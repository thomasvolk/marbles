/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.voolk.marbles.web.pages.base;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.service.ISystemInfoService;
import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.app.MarblesWebApplication;

public abstract class AbstractPage extends WebPage {
	@SpringBean
	private ISystemInfoService systemInfoService;
	private String variant;

	public AbstractPage() {
		super();
		_init();
	}

	protected ISystemInfoService getSystemInfoService() {
		return systemInfoService;
	}

	public AbstractPage(IModel<?> model) {
		super(model);
		_init();
	}

	public AbstractPage(IPageMap pageMap) {
		super(pageMap);
		_init();
	}

	public AbstractPage(PageParameters parameters) {
		super(parameters);
		_init();
	}

	public AbstractPage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
		_init();
	}

	public AbstractPage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
		_init();
	}

	protected IdentSession getIdentSession() {
	    return ((IdentSession) getSession());
	}

	protected MarblesWebApplication getMarblesWebApplication() {
	    return (MarblesWebApplication) getApplication();
	}

    protected User getUser() {
        return getIdentSession().getUser();
    }

    public String getVariation() {
        return variant;
    }

	private void _init() {
    	PageParameters params = getPageParameters();
    	if(params != null) {
    		variant = params.getString("variant");
    	}
    	if(variant == null) {
	    	variant = getClientType().getVariant();
    	}
    	add(CSSPackageResource.getHeaderContribution(AbstractPage.class, 
        		getStyleSheet()));
    	init();
    }
	
	protected String getStyleSheet() {
		return getClientType().getStylesheet();
	}

	protected ClientType getClientType() {
		ClientProperties clientProperties = ((WebClientInfo)Session.get().getClientInfo()).getProperties();
		ClientType ct = ClientType.getInstace(clientProperties);
		return ct;
	}
    
    protected void init() {
    	
    }
}
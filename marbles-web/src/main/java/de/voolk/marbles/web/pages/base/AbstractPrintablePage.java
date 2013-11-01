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
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.IPageRenderer;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.render.MarkdownPageRenderer;

public abstract class AbstractPrintablePage extends AbstractPage {

	@SpringBean
	private IPageRepository pageRepository;
	private transient IPageSession pageSession;
	private transient IPageRenderer pageRenderer;

	public AbstractPrintablePage() {
		super();
	}

	public AbstractPrintablePage(IModel<?> model) {
		super(model);
	}

	public AbstractPrintablePage(IPageMap pageMap) {
		super(pageMap);
	}

	public AbstractPrintablePage(PageParameters parameters) {
		super(parameters);
	}

	public AbstractPrintablePage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
	}

	public AbstractPrintablePage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
	}

	@Override
	protected void init() {
		super.init();
	    add(CSSPackageResource.getHeaderContribution(AbstractPage.class, "print.css"));
	}

	protected IPageRenderer getPageRenderer() {
	    if(pageRenderer == null) {
	        pageRenderer = new MarkdownPageRenderer(getPageSession(),
	        		getMarblesWebApplication().getUrlResolver());
	    }
	    return pageRenderer;
	}

	protected IPageSession getPageSession() {
	    if(pageSession == null) {
	        pageSession = pageRepository.createSession(getUser());
	    }
	    return pageSession;
	}

}
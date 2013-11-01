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
package de.voolk.marbles.web.pages.registration;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;

public class LogoutPage extends AbstractPage {

    public LogoutPage() {
		super();
		invalidateSession();
	}

	public LogoutPage(PageParameters parameters) {
        super(parameters);
        invalidateSession();
    }

	private void invalidateSession() {
		getSession().invalidate();
	}

    public LogoutPage(IModel<?> model) {
		super(model);
	}

	public LogoutPage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
	}

	public LogoutPage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
	}

	public LogoutPage(IPageMap pageMap) {
		super(pageMap);
	}

	protected void init() {
        add(new HeaderPanel("header", getSystemInfoService().getVersion()));
        add(new FooterPanel("footer"));
        add(new BookmarkablePageLink<String>("home", LoginPage.class));
    }
}

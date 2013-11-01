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
package de.voolk.marbles.web.app;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.coding.QueryStringUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import de.voolk.marbles.web.pages.HomePage;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.content.CreatePage;
import de.voolk.marbles.web.pages.content.DisplayPage;
import de.voolk.marbles.web.pages.content.EditPage;
import de.voolk.marbles.web.pages.registration.LoginPage;

public class MarblesWebApplication extends AuthenticatedWebApplication {
    private UrlResolver urlResolver;

    public MarblesWebApplication()    {
	}

	public UrlResolver getUrlResolver() {
        if(urlResolver == null) {
            urlResolver = new UrlResolver(getServletContext().getContextPath());
        }
        return urlResolver;
    }

    protected void initSpringComponentInjector() {
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }

    @Override
    public void init() {
        super.init();
        getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
        initSpringComponentInjector();
        mount(new QueryStringUrlCodingStrategy("/admin", ListUserPage.class));

        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getDisplayPagePath(),
                DisplayPage.class));
        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getEditPagePath(),
        		EditPage.class));
        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getNewPagePath(),
                CreatePage.class));
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return IdentSession.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
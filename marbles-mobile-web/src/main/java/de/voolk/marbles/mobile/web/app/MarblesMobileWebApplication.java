package de.voolk.marbles.mobile.web.app;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;

import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.app.MarblesWebApplication;
import de.voolk.marbles.web.pages.HomePage;
import de.voolk.marbles.web.pages.registration.LoginPage;

public class MarblesMobileWebApplication extends MarblesWebApplication {


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
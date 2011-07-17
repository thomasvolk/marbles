package de.voolk.marbles.web.app;

import org.apache.wicket.Page;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.target.coding.QueryStringUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import de.voolk.marbles.web.HomePage;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.content.CreateContentPage;
import de.voolk.marbles.web.pages.content.DisplayContentPage;
import de.voolk.marbles.web.pages.content.EditContentPage;
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
        initSpringComponentInjector();
        mount(new QueryStringUrlCodingStrategy("/admin", ListUserPage.class));

        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getDisplayPagePath(),
                DisplayContentPage.class));
        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getEditPagePath(),
        		EditContentPage.class));
        mount(new QueryStringUrlCodingStrategy(getUrlResolver().getNewPagePath(),
                CreateContentPage.class));
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
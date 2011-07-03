package de.voolk.marbles.web.pages.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;

import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.panels.FooterPanel;
import de.voolk.marbles.web.panels.HeaderPanel;

public class LoginPage extends WebPage {
    public LoginPage() {
        this(null);
    }

    public LoginPage(PageParameters parameters) {
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
    	add(new SignInPanel("signInPanel", false));
    	add(CSSPackageResource.getHeaderContribution(AbstractPage.class,
    			"default.css"));
    }

}
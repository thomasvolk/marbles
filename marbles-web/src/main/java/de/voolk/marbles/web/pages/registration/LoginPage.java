package de.voolk.marbles.web.pages.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.CSSPackageResource;

import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;

public class LoginPage extends AbstractPage {

    public LoginPage() {
        this(null);
    }

    public LoginPage(PageParameters parameters) {
        super(parameters);
    }

	@Override
	protected void init() {
		add(new HeaderPanel("header", getSystemInfoService().getVersion()));
        add(new FooterPanel("footer"));
    	add(new SignInPanel("signInPanel", false));
    	add(CSSPackageResource.getHeaderContribution(AbstractMenuPage.class,
    			"default.css"));
	}

}
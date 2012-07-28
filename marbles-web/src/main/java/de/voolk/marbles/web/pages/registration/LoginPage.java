package de.voolk.marbles.web.pages.registration;


import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;
import de.voolk.marbles.web.pages.registration.panel.SignInPanel;

public class LoginPage extends AbstractPage {

	@Override
	protected void init() {
		add(new HeaderPanel("header", getSystemInfoService().getVersion()));
        add(new FooterPanel("footer"));
    	add(new SignInPanel("signInPanel"));
	}

}
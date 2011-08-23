package de.voolk.marbles.web.pages.registration;

import org.apache.wicket.PageParameters;
import org.apache.wicket.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.service.ISystemInfoService;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;

public class LoginPage extends WebPage {
	@SpringBean
	private ISystemInfoService systemInfoService;

    public LoginPage() {
        this(null);
    }

    public LoginPage(PageParameters parameters) {
        add(new HeaderPanel("header", systemInfoService.getVersion()));
        add(new FooterPanel("footer"));
    	add(new SignInPanel("signInPanel", false));
    	add(CSSPackageResource.getHeaderContribution(AbstractMenuPage.class,
    			"default.css"));
    }

}
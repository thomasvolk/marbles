package de.voolk.marbles.web.pages.registration;

import javax.servlet.http.Cookie;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.authentication.pages.SignOutPage;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.protocol.http.WebResponse;

import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;

public class LogoutPage extends SignOutPage {
    public LogoutPage() {
        init();
    }

    public LogoutPage(PageParameters parameters) {
        super(parameters);
        init();
    }

    private void init() {
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
        add(CSSPackageResource.getHeaderContribution(AbstractMenuPage.class, "default.css"));
        add(new BookmarkablePageLink<String>("home", LoginPage.class));
        ((WebResponse) RequestCycle.get().getResponse()).addCookie(new Cookie(IdentSession.IDENT_COOKIE, null));
    }
}

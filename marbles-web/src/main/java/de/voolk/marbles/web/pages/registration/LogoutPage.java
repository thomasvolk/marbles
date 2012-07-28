package de.voolk.marbles.web.pages.registration;

import javax.servlet.http.Cookie;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebResponse;

import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;

public class LogoutPage extends AbstractPage {

    public LogoutPage() {
		super();
	}

	public LogoutPage(PageParameters parameters) {
        super(parameters);
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
        ((WebResponse) RequestCycle.get().getResponse()).addCookie(new Cookie(IdentSession.IDENT_COOKIE, null));
    }
}

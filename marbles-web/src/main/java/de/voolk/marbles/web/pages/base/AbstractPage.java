package de.voolk.marbles.web.pages.base;

import org.apache.wicket.Component;
import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.app.MarblesWebApplication;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;
import de.voolk.marbles.web.pages.base.panel.MenuPanel;
import de.voolk.marbles.web.pages.content.DisplayContentPage;
import de.voolk.marbles.web.pages.registration.LogoutPage;

public abstract class AbstractPage extends WebPage {
    public AbstractPage() {
        init();
    }

    protected AbstractPage(IModel<?> model) {
        super(model);
        init();
    }

    protected AbstractPage(IPageMap pageMap) {
        super(pageMap);
        init();
    }

    protected AbstractPage(IPageMap pageMap, IModel<?> model) {
        super(pageMap, model);
        init();
    }

    protected AbstractPage(PageParameters parameters) {
        super(parameters);
        init();
    }

    protected AbstractPage(IPageMap pageMap, PageParameters parameters) {
        super(pageMap, parameters);
        init();
    }

    private void init() {
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
        add(createSidebarPanel("sidebar"));
        add(new Label("title", "Marbles"));
        add(CSSPackageResource.getHeaderContribution(AbstractPage.class, "default.css"));
        add(new BookmarkablePageLink<String>("logout", getLogoutPage()));
        add(new Label("userName", getLogin()));
        add(createMenuPanel());
    }

    private MenuPanel createMenuPanel() {
        MenuPanel menuPanel = new MenuPanel("menu");
        menuPanel.addItem(new StringResourceModel("home", this, null), DisplayContentPage.class);
        if(getIdentSession().isAdmin()) {
            menuPanel.addItem(new StringResourceModel("administration", this, null), ListUserPage.class);
        }
        return menuPanel;
    }

    protected Component createSidebarPanel(String id) {
        return new WebComponent(id);
    }

    protected String getLogin() {
        return getIdentSession().getLogin();
    }

    protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }

    protected Class<? extends org.apache.wicket.Page> getLogoutPage() {
        return LogoutPage.class;
    }

    protected MarblesWebApplication getMarblesWebApplication() {
        return (MarblesWebApplication) getApplication();
    }
}
package de.voolk.marbles.web.pages.base;

import org.apache.wicket.Component;
import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;
import de.voolk.marbles.web.pages.base.panel.MenuPanel;
import de.voolk.marbles.web.pages.content.DisplayPage;
import de.voolk.marbles.web.pages.content.SiteMapPage;
import de.voolk.marbles.web.pages.registration.LogoutPage;

public abstract class AbstractMenuPage extends AbstractPage {

    public AbstractMenuPage() {
		super();
	}

	public AbstractMenuPage(IModel<?> model) {
		super(model);
	}

	public AbstractMenuPage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
	}

	public AbstractMenuPage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
	}

	public AbstractMenuPage(IPageMap pageMap) {
		super(pageMap);
	}

	public AbstractMenuPage(PageParameters parameters) {
		super(parameters);
	}

	protected void init() {
    	super.init();
        add(new HeaderPanel("header", getSystemInfoService().getVersion()));
        add(new FooterPanel("footer"));
        add(createSidebarPanel("sidebar"));
        add(new Label("title", "Marbles"));
        add(new BookmarkablePageLink<String>("logout", getLogoutPage()));
        add(new Label("userName", getLogin()));
        if(getClientType() == ClientType.WEB) {        	
        	add(createMenuPanel());
        }
        postInit();
    }

    protected void postInit() {
	}

	private MenuPanel createMenuPanel() {
        MenuPanel menuPanel = new MenuPanel("menu");
        menuPanel.addItem(new StringResourceModel("home", this, null), DisplayPage.class);
        menuPanel.addItem(new StringResourceModel("sitemap", this, null), SiteMapPage.class);
        if(getIdentSession().isAdmin()) {
            menuPanel.addItem(new StringResourceModel("administration", this, null), ListUserPage.class);
        }
        return menuPanel;
    }

    protected abstract Component createSidebarPanel(String id);

    protected String getLogin() {
        return getIdentSession().getLogin();
    }

    protected Class<? extends org.apache.wicket.Page> getLogoutPage() {
        return LogoutPage.class;
    }
}
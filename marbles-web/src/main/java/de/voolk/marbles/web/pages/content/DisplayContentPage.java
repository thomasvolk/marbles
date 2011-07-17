package de.voolk.marbles.web.pages.content;


import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;

import de.voolk.marbles.api.pages.render.IPageRenderer;
import de.voolk.marbles.web.app.UrlResolver;
import de.voolk.marbles.web.app.render.WebPageRenderer;
import de.voolk.marbles.web.pages.content.sidebar.DisplayPageSidebarPanel;

@AuthorizeInstantiation("user")
public class DisplayContentPage extends AbstractContentPage {
    private transient IPageRenderer pageRenderer;

    public DisplayContentPage(PageParameters parameters) {
        super(parameters);
        Label content = new Label("page", getPageRenderer().toHtml(getMarblesPage()));
        content.setEscapeModelStrings(false);
        add(content);
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new DisplayPageSidebarPanel(id, getMarblesPage().getId());
    }

    protected IPageRenderer getPageRenderer() {
        if(pageRenderer == null) {
            pageRenderer = new WebPageRenderer(getPageSession(), getUrlResolver());
        }
        return pageRenderer;
    }

	protected UrlResolver getUrlResolver() {
		return getMarblesWebApplication().getUrlResolver();
	}

}

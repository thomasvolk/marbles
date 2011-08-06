package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.SiteMapSidebar;

public class SiteMapPage extends AbstractSitePage {
	private Component action;
	private SiteMapSidebar displaySiteMapSidebarPanel;

	public SiteMapPage(PageParameters parameters) {
		super(parameters);
		action = new WebComponent("action");
        add(action);
	}

	@Override
	protected void postInit() {
		super.postInit();
		SiteMapPanel tree = new SiteMapPanel("tree", getMarblesPage().getId(),
				displaySiteMapSidebarPanel);
        add(tree);
	}



	public Component getAction() {
		return action;
	}

	@Override
	protected Component createSidebarPanel(String id) {
		displaySiteMapSidebarPanel = new SiteMapSidebar(id, getMarblesPage().getId(), this);
		return displaySiteMapSidebarPanel;
	}


}

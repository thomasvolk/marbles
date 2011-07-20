package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.DisplaySiteMapSidebarPanel;

public class DisplaySiteMapPage extends AbstractContentPage {
	private Component action;
	private DisplaySiteMapSidebarPanel displaySiteMapSidebarPanel;

	public DisplaySiteMapPage(PageParameters parameters) {
		super(parameters);
		action = new WebComponent("action");
        add(action);
	}

	@Override
	protected void postInit() {
		super.postInit();
		SiteMapPanel tree = new SiteMapPanel("tree", getMarblesPageId(),
				displaySiteMapSidebarPanel);
        add(tree);
	}



	public Component getAction() {
		return action;
	}

	@Override
	protected Component createSidebarPanel(String id) {
		displaySiteMapSidebarPanel = new DisplaySiteMapSidebarPanel(id, this);
		return displaySiteMapSidebarPanel;
	}


}

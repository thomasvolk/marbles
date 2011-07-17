package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;

public class DisplaySiteMapPage extends AbstractContentPage {

	public DisplaySiteMapPage(PageParameters parameters) {
		super(parameters);
		SiteMapPanel tree = new SiteMapPanel("tree", getPageSession(), getMarblesPageId());
        add(tree);
	}

	@Override
	protected Component createSidebarPanel(String id) {
		return new WebComponent(id);
	}


}

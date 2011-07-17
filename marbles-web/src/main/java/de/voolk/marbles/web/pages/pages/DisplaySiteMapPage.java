package de.voolk.marbles.web.pages.pages;

import org.apache.wicket.PageParameters;

import de.voolk.marbles.web.panels.SiteMapPanel;

public class DisplaySiteMapPage extends AbstractContentPage {

	public DisplaySiteMapPage(PageParameters parameters) {
		super(parameters);
		SiteMapPanel tree = new SiteMapPanel("tree", getPageSession(), getMarblesPageId());
        add(tree);
	}

}

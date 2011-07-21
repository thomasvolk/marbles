package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.MoveContentSidebar;

public class MoveContentPage extends AbstractContentPage {
	private Component action;
	private MoveContentSidebar moveContentSidebar;

	@Override
	protected void postInit() {
		super.postInit();
		SiteMapPanel tree = new SiteMapPanel("tree",
				getPageSession().getRootPage().getId(),
				moveContentSidebar);
        add(tree);
	}

	@Override
	protected Component createSidebarPanel(String id) {
		moveContentSidebar = new MoveContentSidebar(id,
				getMarblesPageId(), this);
		return moveContentSidebar;
	}

	public Component getAction() {
		return action;
	}
}

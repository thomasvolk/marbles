package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.MoveSidebar;

public class MovePage extends AbstractSitePage {
	private Component action;
	private MoveSidebar moveContentSidebar;

	public MovePage(PageParameters parameters) {
		super(parameters);
		action = new WebComponent("action");
        add(action);
	}

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
		Integer marblesPageId = getMarblesPageId();
		if(marblesPageId == null) {
			marblesPageId = getPageSession().getRootPage().getId();
		}
		moveContentSidebar = new MoveSidebar(id,
				marblesPageId, this, DisplayPage.class);
		return moveContentSidebar;
	}

	public Component getAction() {
		return action;
	}
}

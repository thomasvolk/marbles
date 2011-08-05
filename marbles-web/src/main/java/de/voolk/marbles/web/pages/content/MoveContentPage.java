package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.MoveContentSidebar;

public class MoveContentPage extends AbstractContentPage {
	private Component action;
	private MoveContentSidebar moveContentSidebar;

	public MoveContentPage(PageParameters parameters) {
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
		moveContentSidebar = new MoveContentSidebar(id,
				marblesPageId, this, DisplayContentPage.class);
		return moveContentSidebar;
	}

	public Component getAction() {
		return action;
	}
}

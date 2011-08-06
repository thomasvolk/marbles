package de.voolk.marbles.web.pages.content;

import org.apache.wicket.model.IModel;

import de.voolk.marbles.web.pages.base.AbstractSidebarPanel;

public abstract class AbstractSitePageSidebar extends AbstractSidebarPanel {
	private static final long serialVersionUID = 1L;

	public AbstractSitePageSidebar(String id) {
		super(id);
	}

	public AbstractSitePageSidebar(String id, IModel<?> model) {
		super(id, model);
	}


}

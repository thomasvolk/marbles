package de.voolk.marbles.web.pages.content;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.voolk.marbles.web.pages.base.AbstractSidebarPanel;

public abstract class AbstractContentSidebarPanel extends AbstractSidebarPanel {
	private static final long serialVersionUID = 1L;

	public AbstractContentSidebarPanel(String id) {
		super(id);
	}

	public AbstractContentSidebarPanel(String id, IModel<?> model) {
		super(id, model);
	}


}

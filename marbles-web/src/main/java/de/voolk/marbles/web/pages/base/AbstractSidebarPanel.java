package de.voolk.marbles.web.pages.base;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class AbstractSidebarPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public AbstractSidebarPanel(String id) {
		super(id);
	}

	public AbstractSidebarPanel(String id, IModel<?> model) {
		super(id, model);
	}

}

package de.voolk.marbles.web.pages.base;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class AbstractSidebar extends Panel {
	private static final long serialVersionUID = 1L;

	public AbstractSidebar(String id) {
		super(id);
	}

	public AbstractSidebar(String id, IModel<?> model) {
		super(id, model);
	}

}

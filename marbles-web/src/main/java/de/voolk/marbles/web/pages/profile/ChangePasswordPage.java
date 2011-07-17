package de.voolk.marbles.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.base.AbstractPage;

// TODO implement this page
public class ChangePasswordPage extends AbstractPage {
	@Override
	protected Component createSidebarPanel(String id) {
		return new WebComponent(id);
	}
}

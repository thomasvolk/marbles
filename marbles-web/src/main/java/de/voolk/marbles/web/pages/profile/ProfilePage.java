package de.voolk.marbles.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;

//TODO implement this page
public class ProfilePage extends AbstractMenuPage {

	public ProfilePage() {
	}

	public User getUser() {
		return getIdentSession().getUser();
	}

	@Override
	protected Component createSidebarPanel(String id) {
		return new WebComponent(id);
	}

}

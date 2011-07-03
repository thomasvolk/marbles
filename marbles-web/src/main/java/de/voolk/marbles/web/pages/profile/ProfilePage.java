package de.voolk.marbles.web.pages.profile;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.web.pages.base.AbstractPage;

public class ProfilePage extends AbstractPage {

	public ProfilePage() {
	}

	public User getUser() {
		return getIdentSession().getUser();
	}
	
}

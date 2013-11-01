/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.voolk.marbles.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;

public class ProfilePage extends AbstractMenuPage {


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ProfilePage() {
        add(new Link<String>("changePasswordPage") {
            @Override
            public void onClick() {
                setResponsePage(ChangePasswordPage.class);
            }
        });
    }

	public User getUser() {
		return getIdentSession().getUser();
	}

	@Override
	protected Component createSidebarPanel(String id) {
		return new WebComponent(id);
	}

}

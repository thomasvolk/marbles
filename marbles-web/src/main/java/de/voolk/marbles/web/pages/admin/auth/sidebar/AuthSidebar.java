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
package de.voolk.marbles.web.pages.admin.auth.sidebar;

import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.web.pages.admin.auth.CreateUserPage;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.base.sidebar.AbstractSidebar;

@SuppressWarnings({"serial", "rawtypes"})
public class AuthSidebar extends AbstractSidebar {
    public AuthSidebar(String id) {
        super(id);
        add(new Link("createUser") {
            @Override
            public void onClick() {
                setResponsePage(CreateUserPage.class);
            }
        });
        add(new Link("listUsers") {
            @Override
            public void onClick() {
                setResponsePage(ListUserPage.class);
            }
        });
    }
}

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
package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.base.sidebar.AbstractSidebar;

@SuppressWarnings({"serial", "rawtypes"})
public class RenameSidebar extends AbstractSidebar {

    public RenameSidebar(String id, final IPage marblesPage, final Class<? extends Page> redirectPage) {
        super(id);

        add(new Link("cancel") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(redirectPage, parameters);
            }
        });
    }
}

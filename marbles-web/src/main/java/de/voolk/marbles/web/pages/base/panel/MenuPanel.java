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
package de.voolk.marbles.web.pages.base.panel;

import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.pages.profile.ProfilePage;
import de.voolk.marbles.web.pages.registration.LogoutPage;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends Panel {
    @SuppressWarnings("serial")
	public static class MenuItem implements Serializable {
        private final String title;
        private final Class<? extends Page> page;

        public MenuItem(String title, Class<? extends Page> page) {
        	 this.title = title;
             this.page = page;
        }

        @SuppressWarnings("rawtypes")
		public Link getLink() {
            @SuppressWarnings("unchecked")
			Link link = new BookmarkablePageLink("link", this.page);
            link.add(new Label("title", new Model<String>(title)));
            return link;
        }

        public Class<? extends Page> getPage() {
            return page;
        }
    }

    private static final long serialVersionUID = 2L;
    private List<MenuItem> items = new ArrayList<MenuItem>();

    @SuppressWarnings("serial")
	public MenuPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<String>("logoutPage", LogoutPage.class));
        BookmarkablePageLink<String> profilePage = new BookmarkablePageLink<String>(
                "profilePage", ProfilePage.class);
        add(profilePage);
        profilePage.add(new Label("userName", getLogin()));
        ListModel<MenuItem> itemsModel = new ListModel<MenuItem>();
        itemsModel.setObject(items);
        add(new ListView<MenuItem>("items", itemsModel) {

            @Override
            protected void populateItem(ListItem<MenuItem> listItem) {
                MenuItem item = listItem.getModelObject();
                if (item.getPage() == getPage().getClass()) {
                    listItem.add(new AttributeAppender("class",
                            new Model<String>("current_page_item"), " "));
                }
                listItem.add(item.getLink());
            }
        });
    }

    protected String getLogin() {
        return getIdentSession().getLogin();
    }

    private IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }

    public void addItem(String name, Class<? extends Page> page) {
        items.add(new MenuItem(name, page));
    }

     public void addItem(IModel<String> model, Class<? extends Page> page) {
        items.add(new MenuItem(model.getObject(), page));
     }
}

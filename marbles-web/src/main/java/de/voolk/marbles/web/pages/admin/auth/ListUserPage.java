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
package de.voolk.marbles.web.pages.admin.auth;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IAuthentificationService;
import de.voolk.marbles.persistence.services.IPageService;
import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.model.UserDataProvider;
import de.voolk.marbles.web.pages.admin.auth.sidebar.AuthSidebar;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.base.panel.ReplacingConfirmationActionPanel;

@AuthorizeInstantiation("admin")
public class ListUserPage extends AbstractMenuPage {
    @SpringBean
    private IAuthentificationService authentificationService;
    @SpringBean
    private IPageService pageService;
    private Component action;
    private User selectedUsertoRemove;

    @SuppressWarnings("serial")
	public ListUserPage() {
        action = new WebComponent("action");
        add(action);
        add(new DataView<User>("userList", new UserDataProvider(authentificationService)) {
        	@SuppressWarnings("rawtypes")
			private Link removeLink;
            @Override
            @SuppressWarnings("rawtypes")
            protected void populateItem(Item<User> userItem) {
                final User user = userItem.getModelObject();
				removeLink = new Link("remove") {
                    @Override
                    public void onClick() {
                    	selectedUsertoRemove = user;
                        ValueMap info = new ValueMap();
                        info.put("user", user.getName());
                        new ReplacingConfirmationActionPanel(action,
                                new StringResourceModel("remove.confirmation",
                                        ListUserPage.this, new Model<ValueMap>(info))) {
                            @Override
                            public void execute() {
                            	pageService.removeAllPages(user);
                                authentificationService.removeUser(user.getId());
                                setResponsePage(ListUserPage.this.getClass());
                            }
                            @Override
        					public void cancel() {
        						super.cancel();
        						selectedUsertoRemove = null;
        					}
                        };
                    }
                };
                String crossPic = "cross.png";
				if(authentificationService.userHasRole(user, IdentSession.SYSTEM_ROLE)
						|| selectedUsertoRemove != null) {
					removeLink.setEnabled(false);
					crossPic = "cross_gray.png";
				}
				removeLink.add(new Image("crossImg",
                        new ResourceReference(ListUserPage.class, crossPic)));
				userItem.add(removeLink);
                userItem.add(new Label("id", String.valueOf(user.getId())));
                userItem.add(new Label("name", user.getName()));
                userItem.add(new Label("email", user.getEmail()));
            }
        });
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new AuthSidebar(id);
    }
}

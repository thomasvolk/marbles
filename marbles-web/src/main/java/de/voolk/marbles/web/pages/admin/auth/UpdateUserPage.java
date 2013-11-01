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
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.persistence.services.IAuthentificationService;
import de.voolk.marbles.web.pages.admin.auth.sidebar.AuthSidebar;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.profile.panel.SetPasswordPanel;

@AuthorizeInstantiation("admin")
public class UpdateUserPage extends AbstractMenuPage {
    @SpringBean
    private IAuthentificationService authentificationService;
    private final FeedbackPanel feedback = new FeedbackPanel("messages");
    private String name;
    private String password;
    private String email;

    @SuppressWarnings({ "rawtypes" })
	public UpdateUserPage(PageParameters parameters) {
        add(feedback);
        @SuppressWarnings("serial")
		Form form = new Form("updateUserForm") {
            @Override
            protected void onSubmit() {
                authentificationService.createUser(name, email, password);
                setResponsePage(ListUserPage.class);
            }
        };
        form.add(new Label("name",
        		new PropertyModel<String>(this, "name")));
        SetPasswordPanel passwordPanel = new SetPasswordPanel("passwordPanel",
        		new PropertyModel<String>(this, "password"));
        form.add(passwordPanel);
        form.add(new Label("email",
        		new PropertyModel<String>(this, "email")));
        form.add(new Button("save", new ResourceModel("save")));
        add(form);
    }
    @Override
    protected Component createSidebarPanel(String id) {
        return new AuthSidebar(id);
    }
}

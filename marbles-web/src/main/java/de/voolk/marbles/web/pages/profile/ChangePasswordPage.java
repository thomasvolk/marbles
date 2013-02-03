package de.voolk.marbles.web.pages.profile;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IAuthentificationService;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.profile.panel.SetPasswordPanel;
import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

// TODO implement this page
public class ChangePasswordPage extends AbstractMenuPage {
    @SpringBean
    private IAuthentificationService authentificationService;
    private final FeedbackPanel feedback = new FeedbackPanel("messages");
    private String password;

    public ChangePasswordPage(PageParameters parameters) {
        add(feedback);
        @SuppressWarnings("serial")
        Form form = new Form("createUserForm") {
            @Override
            protected void onSubmit() {
                authentificationService.changePassword(getUser().getId(), password);
                setResponsePage(ListUserPage.class);
            }
        };

        SetPasswordPanel passwordPanel = new SetPasswordPanel("passwordPanel",
                new PropertyModel<String>(this, "password"));
        form.add(passwordPanel);

        form.add(new Button("save", new ResourceModel("save")));
        add(form);
    }

    public User getUser() {
        return getIdentSession().getUser();
    }

    @Override
	protected Component createSidebarPanel(String id) {
		return new WebComponent(id);
	}
}

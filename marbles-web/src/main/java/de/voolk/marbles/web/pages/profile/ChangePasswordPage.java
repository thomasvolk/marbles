package de.voolk.marbles.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IAuthentificationService;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.profile.panel.SetPasswordPanel;

public class ChangePasswordPage extends AbstractMenuPage {
    @SpringBean
    private IAuthentificationService authentificationService;
    private final FeedbackPanel feedback = new FeedbackPanel("messages");
    private FormComponent<String> oldPasswordField;
    private String password;
    private String oldPassword;
    
    public ChangePasswordPage(PageParameters parameters) {
        add(feedback);
        @SuppressWarnings("serial")
        Form form = new Form("createUserForm") {
            @Override
            protected void onSubmit() {
                authentificationService.changePassword(getUser().getId(), password);
                setResponsePage(ProfilePage.class);
            }
        };
        oldPasswordField = new PasswordTextField("oldPassword",
                new PropertyModel<String>(this, "oldPassword")).setRequired(true);
        form.add(oldPasswordField);

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

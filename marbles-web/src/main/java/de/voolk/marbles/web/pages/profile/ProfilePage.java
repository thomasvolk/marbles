package de.voolk.marbles.web.pages.profile;

import de.voolk.marbles.persistence.services.IAuthentificationService;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.profile.panel.SetPasswordPanel;
import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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

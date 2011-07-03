package de.voolk.marbles.web.panels.auth;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

@SuppressWarnings("serial")
public class SetPasswordPanel extends FormComponentPanel<String> {
    @SuppressWarnings("unused")
	private String password;
    @SuppressWarnings("unused")
	private String passwordRepeat;
    private FormComponent<String> passwordField;
    private FormComponent<String> passwordRepeatField;
    
	public SetPasswordPanel(String id, IModel<String> model) {
		super(id, model);
		passwordField = new PasswordTextField("password", 
        		new PropertyModel<String>(this, "password")).setRequired(true);
		add(passwordField);
        passwordRepeatField = new PasswordTextField("passwordRepeat", 
        		new PropertyModel<String>(this, "passwordRepeat")).setRequired(true);
		add(passwordRepeatField);
	}
	
	protected void convertInput() {
		getForm().add(new EqualPasswordInputValidator(passwordField, passwordRepeatField));
		setConvertedInput(passwordField.getConvertedInput());
	}

}

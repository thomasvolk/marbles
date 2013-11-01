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
package de.voolk.marbles.web.pages.profile.panel;

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

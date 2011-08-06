package de.voolk.marbles.web.pages.content;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.content.sidebar.RenameSidebar;

@AuthorizeInstantiation("user")
public class RenamePage extends AbstractSitePage {

	@SuppressWarnings("serial")
	public RenamePage(PageParameters parameters) {
		super(parameters);
		final TextField<String> pageName = new TextField<String>("pageName",
				new PropertyModel<String>(getMarblesPage(), "name"));
		pageName.add(new IValidator<String>() {
			@Override
			public void validate(IValidatable<String> validatable) {
				String name = validatable.getValue();
				if(!uniqueInLevel(name)) {
					validatable.error(new IValidationError() {
						@Override
						public String getErrorMessage(IErrorMessageSource arg0) {
							return  new StringResourceModel("pagename.not.unique",
									RenamePage.this, new Model<ValueMap>()).getObject();
						}
					});
				}
			}


		});
		pageName.setRequired(true);
		pageName.add(StringValidator.minimumLength(1));
		@SuppressWarnings({"rawtypes"})
		Form form = new Form("renameForm") {
            @Override
            protected void onSubmit() {
            	String name = pageName.getModelObject();
				int id = savePageName(name);
                PageParameters parameters = new PageParameters();
                parameters.put("id", id);
                setResponsePage(DisplayPage.class, parameters);
            }
        };
        form.add(pageName);
        form.add(new Button("save", new ResourceModel("save")));
        add(form);
        add(new FeedbackPanel("feedback"));
	}

	protected int savePageName(String name) {
        Integer id = getMarblesPage().getId();
        getPageSession().renamePage(id, name);
        return id;
    }

	private boolean uniqueInLevel(String name) {
		if(getMarblesPage().isRoot()) {
			return true;
		}
		else {
			List<IPage> children = getPageSession().getChildren(getMarblesPage().getParent().getId());
			for(IPage page: children) {
				if(name.equals(page.getName())) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	protected Component createSidebarPanel(String id) {
		return new RenameSidebar(id, getMarblesPage(), DisplayPage.class);
	}

}



package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import de.voolk.marbles.web.pages.content.sidebar.EditPageSidebarPanel;

@AuthorizeInstantiation("user")
public class EditContentPage extends AbstractContentPage {


    public EditContentPage(PageParameters parameters) {
        super(parameters);
        final TextArea<String> contentEdit = new TextArea<String>("pageContent",
                new PropertyModel<String>(getMarblesPage(), "content"));
        @SuppressWarnings({"serial", "rawtypes"})
		Form form = new Form("editPageForm") {
            @Override
            protected void onSubmit() {
                int id = savePageContent(contentEdit.getModelObject());
                PageParameters parameters = new PageParameters();
                parameters.put("id", id);
                setResponsePage(getDisplayPageClass(), parameters);
            }
        };
        form.add(contentEdit);
        form.add(new Button("save", new ResourceModel("save")));
        add(form);
    }

	protected Class<? extends Page> getDisplayPageClass() {
		return DisplayContentPage.class;
	}

    protected int savePageContent(String content) {
        Integer id = getMarblesPage().getId();
        getPageSession().updatePage(id, content);
        return id;
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new EditPageSidebarPanel(id, getMarblesPage(),
        		getDisplayPageClass());
    }

}



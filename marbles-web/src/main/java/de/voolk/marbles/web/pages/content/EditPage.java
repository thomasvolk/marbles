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

import de.voolk.marbles.web.pages.content.sidebar.EditSidebar;

@AuthorizeInstantiation("user")
public class EditPage extends AbstractSitePage {


    public EditPage(PageParameters parameters) {
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
        form.add(new Button("saveTop", new ResourceModel("save")));
        form.add(new Button("saveBottom", new ResourceModel("save")));
        add(form);
    }

	protected Class<? extends Page> getDisplayPageClass() {
		return DisplayPage.class;
	}

    protected int savePageContent(String content) {
        Integer id = getMarblesPage().getId();
        getPageSession().updatePage(id, content);
        return id;
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new EditSidebar(id, getMarblesPage(),
        		getDisplayPageClass());
    }

}



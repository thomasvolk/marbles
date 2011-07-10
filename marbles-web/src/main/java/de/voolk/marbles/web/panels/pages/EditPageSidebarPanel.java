package de.voolk.marbles.web.panels.pages;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.pages.DisplayContentPage;
import de.voolk.marbles.web.pages.pages.EditContentPage;
import de.voolk.marbles.web.panels.ReplacingConfirmationActionPanel;

@SuppressWarnings({"serial", "rawtypes"})
public class EditPageSidebarPanel extends Panel {

    public EditPageSidebarPanel(final EditContentPage page, String id, final IPage marblesPage,
    		final Class<? extends Page> redirectPage) {
        super(id);

        add(new Link("cancel") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(redirectPage, parameters);
            }
        });
        Link deleteLink = new Link("delete") {
            @Override
            public void onClick() {


            	new ReplacingConfirmationActionPanel(page.getAction(),
                        new StringResourceModel("delete.confirmation",
                        		EditPageSidebarPanel.this, new Model<ValueMap>())) {
                    @Override
                    public void execute() {
                    	PageParameters parameters = new PageParameters();
                        parameters.put("id", marblesPage.getId());
                        setResponsePage(DisplayContentPage.class, parameters);
                    }
                };
            }
        };
        if(marblesPage.isRoot()) {
        	deleteLink.setEnabled(false);
        }
		add(deleteLink);
    }
}

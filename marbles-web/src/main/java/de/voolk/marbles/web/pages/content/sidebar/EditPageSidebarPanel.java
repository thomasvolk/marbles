package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.web.pages.base.panel.ReplacingConfirmationActionPanel;
import de.voolk.marbles.web.pages.content.AbstractContentSidebarPanel;
import de.voolk.marbles.web.pages.content.DeleteContentPage;
import de.voolk.marbles.web.pages.content.DisplaySiteMapPage;
import de.voolk.marbles.web.pages.content.EditContentPage;

@SuppressWarnings({"serial", "rawtypes"})
public class EditPageSidebarPanel extends AbstractContentSidebarPanel {
    public EditPageSidebarPanel(final EditContentPage page, String id, final IPage marblesPage,
    		final Class<? extends Page> redirectPage, IPageSession pageSession) {
        super(id);

        add(new Link("cancel") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(redirectPage, parameters);
            }
        });
        add(new Link("sitemap") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(DisplaySiteMapPage.class, parameters);
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
                        setResponsePage(DeleteContentPage.class, parameters);
                    }
                };
            }
        };
        if(marblesPage.isRoot() || pageSession.hasChildren(marblesPage)) {
        	deleteLink.setEnabled(false);
        }
		add(deleteLink);
    }
}

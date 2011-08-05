package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.pages.base.panel.ReplacingConfirmationActionPanel;
import de.voolk.marbles.web.pages.content.AbstractContentSidebarPanel;
import de.voolk.marbles.web.pages.content.DeleteContentPage;
import de.voolk.marbles.web.pages.content.DisplayContentPage;
import de.voolk.marbles.web.pages.content.EditContentPage;
import de.voolk.marbles.web.pages.content.MoveContentPage;
import de.voolk.marbles.web.pages.content.RenamePage;

@SuppressWarnings({"serial", "rawtypes"})
public class DisplayPageSidebarPanel extends AbstractContentSidebarPanel {
	@SpringBean
    private IPageRepository pageRepository;
    public DisplayPageSidebarPanel(final DisplayContentPage page, String id, final IPage marblesPage) {
        super(id);
        add(new Link("editPage") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(EditContentPage.class, parameters);
            }
        });
        Link deleteLink = new Link("delete") {
            @Override
            public void onClick() {
            	new ReplacingConfirmationActionPanel(page.getAction(),
                        new StringResourceModel("delete.page.confirmation",
                        		DisplayPageSidebarPanel.this, new Model<ValueMap>())) {
                    @Override
                    public void execute() {
                    	PageParameters parameters = new PageParameters();
                        parameters.put("id", marblesPage.getId());
                        setResponsePage(DeleteContentPage.class, parameters);
                    }
                };
            }
        };
        Link moveLink = new Link("move") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(MoveContentPage.class, parameters);
            }
        };
		add(moveLink);
		Link renameLink = new Link("rename") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", marblesPage.getId());
                setResponsePage(RenamePage.class, parameters);
            }
        };
		add(renameLink);

		if(marblesPage.isRoot()) {
			moveLink.setEnabled(false);
		}
        IPageSession session = pageRepository.createSession(getIdentSession().getUser());
        if(marblesPage.isRoot() || session.hasChildren(marblesPage)) {
        	deleteLink.setEnabled(false);
        }
		add(deleteLink);
    }


    protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }

}

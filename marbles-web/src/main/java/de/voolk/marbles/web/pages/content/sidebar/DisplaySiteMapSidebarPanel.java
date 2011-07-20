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
import de.voolk.marbles.web.pages.content.DisplaySiteMapPage;
import de.voolk.marbles.web.pages.content.panel.SiteMapPanel.ISiteMapListener;

@SuppressWarnings({ "rawtypes", "serial" })
public class DisplaySiteMapSidebarPanel extends AbstractContentSidebarPanel implements ISiteMapListener {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private IPageRepository pageRepository;
	private Link deleteLink;
	private IPage selectedPage;

	public DisplaySiteMapSidebarPanel(String id, final int rootPageId, final DisplaySiteMapPage page) {
		super(id);
		deleteLink = new Link("delete") {
			@Override
			 public void onClick() {
            	new ReplacingConfirmationActionPanel(page.getAction(),
                        new StringResourceModel("delete.page.confirmation",
                        		DisplaySiteMapSidebarPanel.this, new Model<ValueMap>())) {
                    @Override
                    public void execute() {
                    	IPageSession session = pageRepository.createSession(getIdentSession().getUser());
                        session.removePage(selectedPage.getId());
                        PageParameters parameters = new PageParameters();
                        parameters.put("id", rootPageId);
                    	setResponsePage(DisplaySiteMapPage.class);
                    }
                };
            }
        };
        deleteLink.setEnabled(false);
        add(deleteLink);
	}

	@Override
	public void pageSelected(IPage page) {
		deleteLink.setEnabled(true);
		selectedPage = page;
	}

	protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }
}

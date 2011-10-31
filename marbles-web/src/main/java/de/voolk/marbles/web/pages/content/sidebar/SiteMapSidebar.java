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
import de.voolk.marbles.web.pages.base.AbstractSidebar;
import de.voolk.marbles.web.pages.base.panel.ReplacingConfirmationActionPanel;
import de.voolk.marbles.web.pages.content.DisplayPage;
import de.voolk.marbles.web.pages.content.MovePage;
import de.voolk.marbles.web.pages.content.SiteMapPage;
import de.voolk.marbles.web.pages.content.PrintSiteMapPage;
import de.voolk.marbles.web.pages.content.panel.SiteMapPanel.ISiteMapListener;

@SuppressWarnings({ "rawtypes", "serial" })
public class SiteMapSidebar extends AbstractSidebar implements ISiteMapListener {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private IPageRepository pageRepository;
	private Link deleteLink;
	private Link openLink;
	private IPage selectedPage;
	private int rootPageId;
	private Link moveLink;

	public SiteMapSidebar(String id, final int rootPageId, final SiteMapPage page) {
		super(id);
		this.rootPageId = rootPageId;
		deleteLink = new Link("delete") {
			@Override
			 public void onClick() {
				this.setEnabled(false);
            	new ReplacingConfirmationActionPanel(page.getAction(),
                        new StringResourceModel("delete.page.confirmation",
                        		SiteMapSidebar.this, new Model<ValueMap>())) {
                    @Override
                    public void execute() {
                    	IPageSession session = pageRepository.createSession(getIdentSession().getUser());
                    	if(!selectedPage.isRoot()) {
                    		session.removePage(selectedPage.getId());
                    	}
                        PageParameters parameters = new PageParameters();
                        parameters.put("id", rootPageId);
                    	setResponsePage(SiteMapPage.class, parameters);
                    }

					@Override
					public void cancel() {
						super.cancel();
						deleteLink.setEnabled(true);
					}

                };
            }
        };
        openLink = new Link("open") {
			@Override
			 public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", selectedPage.getId());
            	setResponsePage(DisplayPage.class, parameters);
            }
        };
        moveLink = new Link("move") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", selectedPage.getId());
                setResponsePage(MovePage.class, parameters);
            }
        };
        add(new Link("print") {
            @Override
            public void onClick() {
                setResponsePage(PrintSiteMapPage.class);
            }
        });
        deleteLink.setEnabled(false);
        openLink.setEnabled(false);
        moveLink.setEnabled(false);
        add(deleteLink);
        add(openLink);
        add(moveLink);
	}


	public int getRootPageId() {
		return rootPageId;
	}


	@Override
	public void pageSelected(IPage page) {
		IPageSession session = pageRepository.createSession(getIdentSession().getUser());
		openLink.setEnabled(true);
		if(!page.isRoot()) {
			moveLink.setEnabled(true);
		}
		if(page.isRoot() || page.getId().equals(getRootPageId()) || session.hasChildren(page)) {
			deleteLink.setEnabled(false);
		}
		else {
			deleteLink.setEnabled(true);
		}
		selectedPage = page;
	}

	protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }
}

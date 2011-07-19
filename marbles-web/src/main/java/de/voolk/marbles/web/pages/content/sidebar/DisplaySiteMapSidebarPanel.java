package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.base.panel.ReplacingConfirmationActionPanel;
import de.voolk.marbles.web.pages.content.AbstractContentSidebarPanel;
import de.voolk.marbles.web.pages.content.DisplaySiteMapPage;
import de.voolk.marbles.web.pages.content.panel.SiteMapPanel.ISiteMapListener;

@SuppressWarnings({ "rawtypes", "serial" })
public class DisplaySiteMapSidebarPanel extends AbstractContentSidebarPanel implements ISiteMapListener {
	private static final long serialVersionUID = 1L;
	private Link deleteLink;

	public DisplaySiteMapSidebarPanel(String id, final DisplaySiteMapPage page) {
		super(id);
		deleteLink = new Link("delete") {
			@Override
            public void onClick() {
            	new ReplacingConfirmationActionPanel(page.getAction(),
                        new StringResourceModel("delete.confirmation",
                        		DisplaySiteMapSidebarPanel.this, new Model<ValueMap>())) {
                    @Override
                    public void execute() {
                    	// TODO implement
                    	throw new UnsupportedOperationException();
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
	}
}

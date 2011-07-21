package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.web.pages.content.AbstractContentSidebarPanel;
import de.voolk.marbles.web.pages.content.DisplaySiteMapPage;
import de.voolk.marbles.web.pages.content.EditContentPage;

@SuppressWarnings({"serial", "rawtypes"})
public class DisplayPageSidebarPanel extends AbstractContentSidebarPanel {
    public DisplayPageSidebarPanel(String id, final int pageId) {
        super(id);
        add(new Link("editPage") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", pageId);
                setResponsePage(EditContentPage.class, parameters);
            }

        });
        add(new Link("sitemap") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", pageId);
                setResponsePage(DisplaySiteMapPage.class, parameters);
            }
        });
    }

}

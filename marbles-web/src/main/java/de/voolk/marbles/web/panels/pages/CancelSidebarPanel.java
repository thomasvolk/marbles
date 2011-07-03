package de.voolk.marbles.web.panels.pages;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings({"serial", "rawtypes"})
public class CancelSidebarPanel extends Panel {
    public CancelSidebarPanel(String id, final int pageId, 
    		final Class<? extends Page> redirectPage) {
        super(id);
        add(new Link("cancel") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", pageId);
                setResponsePage(redirectPage, parameters);
            }
        });
    } 
}

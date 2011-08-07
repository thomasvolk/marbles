package de.voolk.marbles.web.pages.admin.auth.sidebar;

import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.web.pages.admin.auth.CreateUserPage;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import de.voolk.marbles.web.pages.base.AbstractSidebarPanel;

@SuppressWarnings({"serial", "rawtypes"})
public class AuthSidebar extends AbstractSidebarPanel {
    public AuthSidebar(String id) {
        super(id);
        add(new Link("createUser") {
            @Override
            public void onClick() {
                setResponsePage(CreateUserPage.class);
            }
        });
        add(new Link("listUsers") {
            @Override
            public void onClick() {
                setResponsePage(ListUserPage.class);
            }
        });
    }
}

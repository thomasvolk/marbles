package de.voolk.marbles.web.pages.admin.auth.sitebar;

import de.voolk.marbles.web.pages.admin.auth.CreateUserPage;
import de.voolk.marbles.web.pages.admin.auth.ListUserPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings({"serial", "rawtypes"})
public class AuthSidebarPanel extends Panel {
    public AuthSidebarPanel(String id) {
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

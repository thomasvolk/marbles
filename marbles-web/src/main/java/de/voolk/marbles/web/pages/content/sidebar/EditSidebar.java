package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.base.AbstractSidebar;

@SuppressWarnings({"serial", "rawtypes"})
public class EditSidebar extends AbstractSidebar {
    public EditSidebar(String id, final IPage marblesPage,
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

    }

}

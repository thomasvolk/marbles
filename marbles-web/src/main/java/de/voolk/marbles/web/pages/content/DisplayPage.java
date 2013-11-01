/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.voolk.marbles.web.pages.content;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;

import de.voolk.marbles.api.pages.render.IPageRenderer;
import de.voolk.marbles.web.app.render.MarkdownPageRenderer;
import de.voolk.marbles.web.pages.content.sidebar.DisplaySidebar;

@AuthorizeInstantiation("user")
public class DisplayPage extends AbstractSitePage {
    private transient IPageRenderer pageRenderer;
    private Component action;

    public DisplayPage(PageParameters parameters) {
        super(parameters);
        action = new WebComponent("action");
        add(action);
        Label content = new Label("page", getPageRenderer().toHtml(getMarblesPage()));
        content.setEscapeModelStrings(false);
        add(content);
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new DisplaySidebar(this, id, getMarblesPage());
    }

    protected IPageRenderer getPageRenderer() {
        if(pageRenderer == null) {
            pageRenderer = new MarkdownPageRenderer(getPageSession(),
            		getMarblesWebApplication().getUrlResolver());
        }
        return pageRenderer;
    }

    public Component getAction() {
		return action;
	}


}

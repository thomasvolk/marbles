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
import org.apache.wicket.markup.html.WebComponent;

import de.voolk.marbles.web.pages.content.panel.SiteMapPanel;
import de.voolk.marbles.web.pages.content.sidebar.SiteMapSidebar;

public class SiteMapPage extends AbstractSitePage {
	private Component action;
	private SiteMapSidebar displaySiteMapSidebarPanel;

	public SiteMapPage(PageParameters parameters) {
		super(parameters);
		action = new WebComponent("action");
        add(action);
	}

	@Override
	protected void postInit() {
		super.postInit();
		SiteMapPanel tree = new SiteMapPanel("tree", getMarblesPage().getId(),
				displaySiteMapSidebarPanel);
        add(tree);
	}



	public Component getAction() {
		return action;
	}

	@Override
	protected Component createSidebarPanel(String id) {
		displaySiteMapSidebarPanel = new SiteMapSidebar(id, getMarblesPage().getId(), this);
		return displaySiteMapSidebarPanel;
	}


}

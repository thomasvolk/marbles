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

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.value.ValueMap;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.web.pages.base.AbstractPrintablePage;

public class PrintSiteMapPage extends AbstractPrintablePage {
	class SiteMapExporter implements IPageTraversationHandler {
		private final StringBuilder pages = new StringBuilder();
		private final StringBuilder toc = new StringBuilder();
		@Override
		public void handle(IPage currentPage, IPage parent) {			
			String anchor = String.format("page%d", currentPage.getId());
			pages.append(String.format("\n\n<a name=\"%s\"/>", anchor));
			pages.append(String.format("<h2>%s</h2>", currentPage.getName()));
			pages.append(getPageRenderer().toHtml(currentPage));
			String html = String.format("<li><a href=\"#%s\">%s</a></li>\n",anchor, currentPage.getName());
			toc.append(html);
		}	
		
		public String toString()   {
			String tocHtml = "<ul>\n" + toc.toString() + "</ul>\n";
			return tocHtml + pages.toString();
		}
	}
 
	public PrintSiteMapPage(PageParameters parameters) {
		super(parameters);
        Label content = new Label("page", getSitemapHtml());
        content.setEscapeModelStrings(false);
        add(content);
        add(new Label("title", new StringResourceModel("sitemap",
        		PrintSiteMapPage.this, new Model<ValueMap>())));
	}

	private String getSitemapHtml() {
		int rootPageId = getPageSession().getRootPage().getId();
		SiteMapExporter exporter = new SiteMapExporter();
		getPageSession().traverse(rootPageId, exporter);
		return exporter.toString();
	}

}

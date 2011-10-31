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

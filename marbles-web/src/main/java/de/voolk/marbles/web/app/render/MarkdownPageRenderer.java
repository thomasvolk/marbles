package de.voolk.marbles.web.app.render;


import com.petebevin.markdown.MarkdownProcessor;

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.filter.IContentFilter;
import de.voolk.marbles.web.app.IUrlResolver;
import de.voolk.marbles.web.app.render.resolver.PageLinkResolver;

public class MarkdownPageRenderer extends HtmlPageRenderer {
	public MarkdownPageRenderer(IPageSession pageSession, IUrlResolver urlResolver) {
        super(pageSession, urlResolver);
        init();
    }
	
	private void init() {
		setLinkResolver(new PageLinkResolver(getUrlResolver(), getPageSession()));
		addContentFilter(new IContentFilter() {
			private MarkdownProcessor markdownProcessor = new MarkdownProcessor();
			@Override
			public String filter(String original) {
				return markdownProcessor.markdown(original);
			}
		});
	}
}

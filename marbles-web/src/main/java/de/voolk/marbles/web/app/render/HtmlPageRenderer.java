package de.voolk.marbles.web.app.render;

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.PageRenderer;
import de.voolk.marbles.web.app.IUrlResolver;

public class HtmlPageRenderer extends PageRenderer {
	protected final IUrlResolver urlResolver;
	protected final IPageSession pageSession;

	public HtmlPageRenderer(IPageSession pageSession, IUrlResolver urlResolver) {
		super();
		this.urlResolver = urlResolver;
		this.pageSession = pageSession;
	}

	public IPageSession getPageSession() {
	    return pageSession;
	}

	public IUrlResolver getUrlResolver() {
	    return urlResolver;
	}

}
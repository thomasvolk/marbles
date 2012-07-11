package de.voolk.marbles.web.app.render.resolver;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.ILinkResolver;
import de.voolk.marbles.web.app.IUrlResolver;

public class PageLinkResolver implements ILinkResolver {
	private final IUrlResolver urlResolver;
    private final IPageSession pageSession;
    
	public PageLinkResolver(IUrlResolver urlResolver, IPageSession pageSession) {
		super();
		this.urlResolver = urlResolver;
		this.pageSession = pageSession;
	}

	public IUrlResolver getUrlResolver() {
		return urlResolver;
	}

	public IPageSession getPageSession() {
		return pageSession;
	}

	@Override
    public String renderPageLink(IPage parentPage, String name) {
        Integer id = resovePage(parentPage, name);
        String linkText = name;
        String url;
        String classAttr = "";
        if(id != null) {
            url = getUrlResolver().getDisplayPageLink(id);
        }
        else {
            classAttr = "class=\"create\"";
            url = getUrlResolver().getNewPageLink(parentPage,name);
        }
        return String.format("<a %s href=\"%s\">%s</a>", classAttr, url, linkText);
    }
	
	private Integer resovePage(IPage parentPage, String name) {
        IPage page = getPageSession().findPageByParentAndName(parentPage, name);
        return page == null ? null : page.getId();
    }
}

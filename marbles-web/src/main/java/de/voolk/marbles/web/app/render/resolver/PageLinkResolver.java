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

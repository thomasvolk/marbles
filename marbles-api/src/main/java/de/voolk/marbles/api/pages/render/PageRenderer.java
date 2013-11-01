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
package de.voolk.marbles.api.pages.render;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.render.filter.IContentFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageRenderer implements IPageRenderer {
    private final List<IContentFilter> contentFilters = new ArrayList<IContentFilter>();
    private ILinkResolver linkResolver;
    private static final String PAGE_PREFIX = "%%";

    private List<IContentFilter> getContentFilters() {
        return contentFilters;
    }

    private ILinkResolver getLinkResolver() {
        return linkResolver;
    }

    @Override
    public void setLinkResolver(ILinkResolver linkResolver) {
        this.linkResolver = linkResolver;
    }

    @Override
    public void addContentFilter(IContentFilter filter) {
        getContentFilters().add(filter);
    }

    @Override
    public String toHtml(IPage page) {
        String content = page.getContent();
        if(content == null) {
        	content = "";
        }
        for(IContentFilter filter: getContentFilters()) {
            content = filter.filter(content);
        }
        return doResolveLinks(page, content);
    }

    private String doResolveLinks(IPage parentPage, String content) {
        if(getLinkResolver() != null) {
            Pattern pattern = Pattern.compile("\\" + PAGE_PREFIX + "[a-zA-Z0-9]+");
            Matcher matcher = pattern.matcher(content);
            StringBuffer result = new StringBuffer();

            while(matcher.find()) {
                String pageName = content.substring(matcher.start() +
                		PAGE_PREFIX.length(), matcher.end());
                matcher.appendReplacement(result,
                        getLinkResolver().renderPageLink(parentPage, pageName));
            }
            matcher.appendTail(result);
            return result.toString();
        }
        else {
            return content;
        }
    }
}

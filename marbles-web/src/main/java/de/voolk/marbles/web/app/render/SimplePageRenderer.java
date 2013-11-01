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
package de.voolk.marbles.web.app.render;

import org.apache.commons.lang.StringEscapeUtils;

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.filter.IContentFilter;
import de.voolk.marbles.web.app.IUrlResolver;
import de.voolk.marbles.web.app.render.filter.ExternalLinkFilter;
import de.voolk.marbles.web.app.render.resolver.PageLinkResolver;

public class SimplePageRenderer extends HtmlPageRenderer {
    public SimplePageRenderer(IPageSession pageSession, IUrlResolver urlResolver) {
        super(pageSession, urlResolver);
        init();
    }

    private void init() {
        setLinkResolver(new PageLinkResolver(getUrlResolver(), getPageSession()));
        
        addContentFilter(new IContentFilter() {
            @Override
            public String filter(String original) {
                return StringEscapeUtils.escapeHtml(original);
            }
        });
        addContentFilter(new IContentFilter() {
            @Override
            public String filter(String content) {
                StringBuilder result = new StringBuilder();

                int count = 0;
                String[] parts = content.split("\\$\\$\\$");
                for(String part: parts) {
                    if(count % 2 == 0) {
                        result.append(part.replace("\n", "\n<br/>\n"));
                    }
                    else {
                        result.append("<pre>").append(part).append("</pre>");
                    }
                    count++;
                }
                return result.toString();
            }
        });
        addContentFilter(new ExternalLinkFilter());
    }
}

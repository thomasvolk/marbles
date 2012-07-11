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

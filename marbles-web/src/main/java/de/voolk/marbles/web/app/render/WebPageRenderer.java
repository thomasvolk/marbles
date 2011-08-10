package de.voolk.marbles.web.app.render;


import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.ILinkResolver;
import de.voolk.marbles.api.pages.render.PageRenderer;
import de.voolk.marbles.api.pages.render.filter.AbstractRegularExpressionFilter;
import de.voolk.marbles.api.pages.render.filter.IContentFilter;
import de.voolk.marbles.web.app.IUrlResolver;
import org.apache.commons.lang.StringEscapeUtils;

public class WebPageRenderer extends PageRenderer {
	public static final String[] IMAGE_SUFFIXES = {
		".gif", ".jpg", ".jpeg", ".jpe", ".png", ".tif", ".tiff"
	};
	public static boolean hasImageSuffix(String original) {
		for(String suffix: IMAGE_SUFFIXES) {
			if(original.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

    private final IUrlResolver urlResolver;
    private final IPageSession pageSession;
    public WebPageRenderer(IPageSession pageSession, IUrlResolver urlResolver) {
        this.pageSession = pageSession;
        this.urlResolver = urlResolver;
        init();
    }

    public IPageSession getPageSession() {
        return pageSession;
    }

    public IUrlResolver getUrlResolver() {
        return urlResolver;
    }

    private Integer resovePage(IPage parentPage, String name) {
        IPage page = getPageSession().findPageByParentAndName(parentPage, name);
        return page == null ? null : page.getId();
    }

    private void init() {
        setLinkResolver(new ILinkResolver() {
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
        });
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
        addContentFilter(new AbstractRegularExpressionFilter() {
            @Override
            protected String getExpression() {
                return "((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)";
            }

            @Override
            protected String getReplacement(String original) {
            	if(original.startsWith("http") && hasImageSuffix(original)) {
            		return String.format("<img alt=\"%s\" src=\"%s\"/>", original, original);
            	}
            	else {
            		return String.format("<a href=\"%s\">%s</a>", original, original);
            	}
            }
        });
    }
}

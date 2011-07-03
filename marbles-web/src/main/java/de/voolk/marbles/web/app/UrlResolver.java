package de.voolk.marbles.web.app;


import de.voolk.marbles.api.beans.IPage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlResolver implements IUrlResolver {
    private static final String EDIT_PAGE_PATH = "/page/edit";
    private static final String DISPLAY_PAGE_PATH = "/page";
    private static final String NEW_PAGE_PATH = "/page/new";
    private final String contextPath;

    public UrlResolver(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String getDisplayPageLink(int pageId) {
        return String.format("%s%s?id=%s", getContextPath(), getDisplayPagePath(),
                urlEncode(String.valueOf(pageId)));
    }

    @Override
    public String getNewPageLink(IPage parent, String name) {
        return String.format("%s%s?parent=%s&name=%s", getContextPath(), getNewPagePath(),
                urlEncode(parent.getId()), urlEncode(name));
    }

    private String urlEncode(Object text) {
        try {
            return URLEncoder.encode(text.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDisplayPagePath() {
        return DISPLAY_PAGE_PATH;
    }

    public String getNewPagePath() {
        return NEW_PAGE_PATH;
    }

	public String getEditPagePath() {
		return EDIT_PAGE_PATH;
	}
}

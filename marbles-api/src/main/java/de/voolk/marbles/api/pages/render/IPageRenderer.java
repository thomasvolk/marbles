package de.voolk.marbles.api.pages.render;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.render.filter.IContentFilter;

public interface IPageRenderer {

    void setLinkResolver(ILinkResolver linkResolver);

    void addContentFilter(IContentFilter filter);

    String toHtml(IPage page);
}

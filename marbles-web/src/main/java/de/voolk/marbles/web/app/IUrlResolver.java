package de.voolk.marbles.web.app;

import de.voolk.marbles.api.beans.IPage;

public interface IUrlResolver {
    String getDisplayPageLink(int pageId);

    String getNewPageLink(IPage parent, String name);
}

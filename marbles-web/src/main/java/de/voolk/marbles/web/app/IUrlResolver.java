package de.voolk.marbles.web.app;

import de.voolk.marbles.api.beans.IPage;

/**
 * Created by IntelliJ IDEA.
 * User: thomas
 * Date: 27.02.11
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public interface IUrlResolver {
    String getDisplayPageLink(int pageId);

    String getNewPageLink(IPage parent, String name);
}

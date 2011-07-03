package de.voolk.marbles.web.app.render;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.app.IUrlResolver;

public class MockUrlResolver implements IUrlResolver {
    @Override
    public String getDisplayPageLink(int pageId) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public String getNewPageLink(IPage parent, String name) {
        throw new UnsupportedOperationException("not implemented");
    }
}

package de.voolk.marbles.web.app.render;


import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;

import java.util.List;

public class MockPageSession implements IPageSession {
    @Override
    public IPage createPage(int parentPageId, String name, String content) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage findPageById(int id) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void updatePage(int id, String content) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage getRootPage() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void removePage(int id) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage findPageByParentAndName(IPage parent, String name) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<IPage> getPagePath(IPage page) {
        throw new UnsupportedOperationException("not implemented");
    }
}

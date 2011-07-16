package de.voolk.marbles.pages;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IPageService;

import java.util.List;

public class PageSession implements IPageSession {
    private final IPageService pageService;
    private final User user;

    public PageSession(User user, IPageService pageService) {
        this.user = user;
        this.pageService = pageService;
    }

    public IPageService getPageService() {
        return pageService;
    }

    public User getUser() {
        return user;
    }

    @Override
    public IPage createPage(int parentPageId, String name, String content) {
        return getPageService().createPage(getUser(), parentPageId, name, content);
    }

    @Override
    public IPage findPageById(int id) {
        return getPageService().findPageByUserAndId(getUser(), id);
    }

    @Override
    public void updatePage(int id, String content) {
        getPageService().updatePage(getUser(), id, content);
    }

    @Override
    public IPage getRootPage() {
        return getPageService().getRootPage(getUser());
    }

    @Override
    public void removePage(int id) {
        getPageService().removePage(getUser(), id);
    }

    @Override
    public IPage findPageByParentAndName(IPage parent, String name) {
        return getPageService().findPageByUserAndParentAndName(getUser(), parent, name);
    }

    @Override
    public List<IPage> getPagePath(IPage page) {
        return getPageService().getPagePath(getUser(), page);
    }

	@Override
	public boolean hasChildren(IPage page) {
		return getPageService().hasChildren(page);
	}

	@Override
	public void traverse(int pageId, IPageTraversationHandler handler) {
		getPageService().traverse(getUser(), pageId, handler);
	}
}

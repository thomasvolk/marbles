package de.voolk.marbles.pages;


import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.services.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageRepository implements IPageRepository {
    @Autowired
    private IPageService pageService;

    public IPageService getPageService() {
        return pageService;
    }

    @Override
    public IPageSession createSession(User user) {
        return new PageSession(user, getPageService());
    }
}

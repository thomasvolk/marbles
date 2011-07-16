package de.voolk.marbles.persistence.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.beans.IUser;
import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.persistence.PageTraverser;
import de.voolk.marbles.persistence.beans.Page;
import de.voolk.marbles.persistence.beans.User;

@Transactional
@Component
public class PageService extends AbstractEntityService<Page> implements IPageService {

    public Page findById(int id) {
        return findById(Page.class, id);
    }

    @Override
    public void updatePage(IUser user, int id, String content) {
        Page page = findPageByUserAndId(user, id);
        page.setContent(content);
        persist(page);
    }

    @Override
    public Page findPageByUserAndId(IUser user, int id) {
        Query query = getEntityManager().createNamedQuery("page:byUserAndId");
        query.setParameter("user", user);
        query.setParameter("id", id);
        return (Page) query.getSingleResult();
    }

    @Override
    public IPage findPageByUserAndParentAndName(IUser user, IPage parent, String name) {
        Query query = getEntityManager().createNamedQuery("page:byUserAndParentAndName");
        query.setParameter("user", user);
        query.setParameter("parent", resolve(Page.class, parent));
        query.setParameter("name", name);
        try {
            return (Page) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void removePage(IUser user, int id) {
        remove(findPageByUserAndId(user, id));
    }

    @Override
	public Page getRootPage(IUser user) {
		Query query = getEntityManager().createNamedQuery("page:rootPage");
        query.setParameter("user", user);
        try {
            return (Page) query.getSingleResult();
        }
        catch (NoResultException e) {
            Page page = new Page();
            page.setContent("This is the ROOT Page please edit ...");
            page.setUser(resolve(User.class, user));
            persist(page);
            return page;
        }
	}

    @Override
    public Page createPage(IUser user, int parentPageId, String name, String content) {
        Page parent = findById(parentPageId);
        Page page = new Page();
        page.setParent(parent);
        page.setName(name);
        page.setContent(content);
        if(user == null) {
            page.setUser(parent.getUser());
        }
        else {
            page.setUser(resolve(User.class, user));
        }
        persist(page);
        return page;
    }

    @Override
    public List<IPage> getPagePath(IUser user, IPage page) {
        Page currentPage = resolve(Page.class, page);
        List<IPage> path = new ArrayList<IPage>();
        path.add(currentPage);
        while(!currentPage.isRoot()) {
            currentPage = currentPage.getParent();
            path.add(currentPage);
        }
        Collections.reverse(path);
        return path;
    }

	@Override
	public void removeAllPages(User user) {
		remove(getRootPage(user));
	}

	@Override
	public boolean hasChildren(IPage page) {
		Query query = getEntityManager().createNamedQuery("page:hasChildren");
        query.setParameter("parent", page);
		return ((Number) query.getSingleResult()).intValue() > 0;
	}

	public void traverse(IUser user, int pageId, IPageTraversationHandler handler) {
		Page root = findPageByUserAndId(user, pageId);
		PageTraverser traverser = new PageTraverser(handler);
		traverser.traverse(root);
	}
}

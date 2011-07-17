package de.voolk.marbles.web.pages.content;


import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.IdentSession;

@AuthorizeInstantiation("user")
public class DeleteContentPage extends WebPage {
	@SpringBean
    private IPageRepository pageRepository;
	private transient IPageSession pageSession;
    private transient IPage page;

    public DeleteContentPage(PageParameters parameters) {
        super(parameters);
        IPageSession pageSession = pageRepository.createSession(getIdentSession().getUser());
        IPage marblesPage = getMarblesPage();
        pageSession.removePage(getMarblesPageId());
        PageParameters displayParams = new PageParameters();
        parameters.put("id", marblesPage.getParent());
        setResponsePage(DisplayContentPage.class, displayParams);
    }

    protected IPageSession getPageSession() {
        if(pageSession == null) {
            pageSession = pageRepository.createSession(getIdentSession().getUser());
        }
        return pageSession;
    }

    protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }

    protected Integer getMarblesPageId() {
        return getPageParameters().getAsInteger("id");
    }

    protected IPage getMarblesPage() {
        if(page == null) {
            if(getMarblesPageId() == null) {
                page = getPageSession().getRootPage();
            }
            else {
                page = getPageSession().findPageById(getMarblesPageId());
            }
        }
        return page;
    }
}



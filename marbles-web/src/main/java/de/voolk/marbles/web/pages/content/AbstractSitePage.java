package de.voolk.marbles.web.pages.content;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.pages.base.AbstractMenuPage;
import de.voolk.marbles.web.pages.content.panel.BreadCrumbPanel;

public abstract class AbstractSitePage extends AbstractMenuPage {
    @SpringBean
    private IPageRepository pageRepository;
    private transient IPageSession pageSession;
    private transient IPage page;

    protected IPageSession getPageSession() {
        if(pageSession == null) {
            pageSession = pageRepository.createSession(getUser());
        }
        return pageSession;
    }

    public AbstractSitePage() {
        this(null);
    }

    protected List<IPage> getMarblesPagePath() {
        return getPageSession().getPagePath(getMarblesPage());
    }

    public AbstractSitePage(PageParameters parameters) {
        super(parameters);
        add(getBreadCrumbPanel());
    }

	protected BreadCrumbPanel getBreadCrumbPanel() {
		return new BreadCrumbPanel("breadcrumb", getMarblesPagePath());
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

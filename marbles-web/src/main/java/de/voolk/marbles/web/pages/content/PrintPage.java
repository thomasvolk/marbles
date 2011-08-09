package de.voolk.marbles.web.pages.content;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.IPageRenderer;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.render.WebPageRenderer;
import de.voolk.marbles.web.pages.base.AbstractPage;

public class PrintPage extends AbstractPage {
	@SpringBean
    private IPageRepository pageRepository;
	private transient IPageSession pageSession;
	private transient IPage page;
	private transient IPageRenderer pageRenderer;

	public PrintPage(PageParameters parameters) {
		super(parameters);
        Label content = new Label("page", getPageRenderer().toHtml(getMarblesPage()));
        content.setEscapeModelStrings(false);
        add(content);
        add(new Label("title", new Model<String>(getMarblesPage().getName())));
	}

	@Override
	protected void init() {
		super.init();
        add(CSSPackageResource.getHeaderContribution(AbstractPage.class, "print.css"));
	}

	protected IPageRenderer getPageRenderer() {
        if(pageRenderer == null) {
            pageRenderer = new WebPageRenderer(getPageSession(),
            		getMarblesWebApplication().getUrlResolver());
        }
        return pageRenderer;
    }

	protected IPageSession getPageSession() {
        if(pageSession == null) {
            pageSession = pageRepository.createSession(getUser());
        }
        return pageSession;
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

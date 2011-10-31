package de.voolk.marbles.web.pages.base;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.IPageRenderer;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.render.WebPageRenderer;

public abstract class AbstractPrintablePage extends AbstractPage {

	@SpringBean
	private IPageRepository pageRepository;
	private transient IPageSession pageSession;
	private transient IPageRenderer pageRenderer;

	public AbstractPrintablePage() {
		super();
	}

	public AbstractPrintablePage(IModel<?> model) {
		super(model);
	}

	public AbstractPrintablePage(IPageMap pageMap) {
		super(pageMap);
	}

	public AbstractPrintablePage(PageParameters parameters) {
		super(parameters);
	}

	public AbstractPrintablePage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
	}

	public AbstractPrintablePage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
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

}
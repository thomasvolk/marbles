package de.voolk.marbles.web.pages.base;

import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.app.MarblesWebApplication;

public abstract class AbstractPage extends WebPage {

	public AbstractPage() {
		super();
		init();
	}

	public AbstractPage(IModel<?> model) {
		super(model);
		init();
	}

	public AbstractPage(IPageMap pageMap) {
		super(pageMap);
		init();
	}

	public AbstractPage(PageParameters parameters) {
		super(parameters);
		init();
	}

	public AbstractPage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
		init();
	}

	public AbstractPage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
		init();
	}

	protected IdentSession getIdentSession() {
	    return ((IdentSession) getSession());
	}

	protected MarblesWebApplication getMarblesWebApplication() {
	    return (MarblesWebApplication) getApplication();
	}

    protected User getUser() {
        return getIdentSession().getUser();
    }

    protected void init() {
    }
}
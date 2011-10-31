package de.voolk.marbles.web.pages.content;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.base.AbstractPrintablePage;

public class PrintPage extends AbstractPrintablePage {
	private transient IPage page;
	public PrintPage(PageParameters parameters) {
		super(parameters);
        Label content = new Label("page", getPageRenderer().toHtml(getMarblesPage()));
        content.setEscapeModelStrings(false);
        add(content);
        add(new Label("title", new Model<String>(getMarblesPage().getName())));
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

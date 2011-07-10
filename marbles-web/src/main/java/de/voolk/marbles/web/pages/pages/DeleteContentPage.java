package de.voolk.marbles.web.pages.pages;


import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.pages.IPageRepository;

@AuthorizeInstantiation("user")
public class DeleteContentPage extends AbstractContentPage {
	@SuppressWarnings("unused")
	@SpringBean
    private IPageRepository pageRepository;

    public DeleteContentPage(PageParameters parameters) {
        super(parameters);
        IPage marblesPage = getMarblesPage();
        getPageSession().removePage(marblesPage.getId());
        PageParameters displayParams = new PageParameters();
        parameters.put("id", marblesPage.getParent());
        setResponsePage(DisplayContentPage.class, displayParams);
    }

}

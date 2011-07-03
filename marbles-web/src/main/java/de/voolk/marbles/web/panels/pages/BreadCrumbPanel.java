package de.voolk.marbles.web.panels.pages;


import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.persistence.services.IPageService;
import de.voolk.marbles.web.pages.pages.DisplayContentPage;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

@SuppressWarnings({"serial", "unused", "rawtypes"})
public class BreadCrumbPanel extends Panel {
    @SpringBean
    private IPageService pageService;

    public BreadCrumbPanel(String id, List<IPage> path) {
        super(id);
        ListModel<IPage> pagesModel = new ListModel<IPage>();
        pagesModel.setObject(path);
        add(new ListView<IPage>("pages", pagesModel) {

            @Override
            @SuppressWarnings("unchecked")
            protected void populateItem(ListItem<IPage> iPageListItem) {
                IPage page = iPageListItem.getModelObject();
                PageParameters parameters = new PageParameters();
                IModel name = new Model<String>(page.getName());

                parameters.put("id", page.getId());
                Link link = new BookmarkablePageLink("link", 
                		getDisplayPageClass(), parameters);
                if(page.isRoot()) {
                    name = new StringResourceModel("root", null);
                }
                link.add(new Label("name", name));
                iPageListItem.add(link);
            }

        });
    }

    protected Class<? extends Page> getDisplayPageClass() {
		return DisplayContentPage.class;
	}    
}

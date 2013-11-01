/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.voolk.marbles.web.pages.content.panel;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.persistence.services.IPageService;
import de.voolk.marbles.web.pages.content.DisplayPage;

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
                link.add(new Label("name", name));
                String sep;
                if(page.isRoot()) {
                	sep = "";
                }
                else {
                	sep = ">";
                }
                Label separator = new Label("sep", sep);
                iPageListItem.add(link);
                iPageListItem.add(separator);
            }

        });
    }

    protected Class<? extends Page> getDisplayPageClass() {
		return DisplayPage.class;
	}
}

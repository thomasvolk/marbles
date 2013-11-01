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

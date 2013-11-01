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
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.IdentSession;

@AuthorizeInstantiation("user")
public class DeletePage extends WebPage {
	@SpringBean
    private IPageRepository pageRepository;
	private transient IPageSession pageSession;
    private transient IPage page;

    public DeletePage(PageParameters parameters) {
        super(parameters);
        IPage marblesPage = getMarblesPage();
        if(marblesPage.isRoot()) {
        	throw new IllegalStateException("can not delete root page!");
        }
        else {
	        Integer parentId = marblesPage.getParent().getId();
	        getPageSession().removePage(marblesPage.getId());
	        PageParameters displayParams = new PageParameters();
			parameters.put("id", parentId);
			setResponsePage(DisplayPage.class, displayParams);
        }
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
            if(getMarblesPageId() != null) {
                page = getPageSession().findPageById(getMarblesPageId());
            }
        }
        return page;
    }
}



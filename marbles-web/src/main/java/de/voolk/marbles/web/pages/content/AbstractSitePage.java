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

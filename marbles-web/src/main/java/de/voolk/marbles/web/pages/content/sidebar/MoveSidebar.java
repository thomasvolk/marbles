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
package de.voolk.marbles.web.pages.content.sidebar;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.IdentSession;
import de.voolk.marbles.web.pages.base.sidebar.AbstractSidebar;
import de.voolk.marbles.web.pages.content.DisplayPage;
import de.voolk.marbles.web.pages.content.MovePage;
import de.voolk.marbles.web.pages.content.panel.SiteMapPanel.ISiteMapListener;

@SuppressWarnings({ "rawtypes", "serial" })
public class MoveSidebar extends AbstractSidebar implements ISiteMapListener {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private IPageRepository pageRepository;
	private Link moveLink;
	private IPage selectedPage;
	private int rootPageId;

	public MoveSidebar(String id, final int rootPageId, final MovePage page,
			final Class<? extends Page> redirectPage) {
		super(id);
		this.rootPageId = rootPageId;
		moveLink = new Link("move") {
			@Override
			 public void onClick() {
            	IPageSession session = pageRepository.createSession(getIdentSession().getUser());
            	if(!selectedPage.getId().equals(rootPageId)) {
            		session.movePageTo(rootPageId, selectedPage.getId());
            	}
            	PageParameters parameters = new PageParameters();
                parameters.put("id", selectedPage.getId());
            	setResponsePage(redirectPage, parameters);
            }
        };
        moveLink.setEnabled(false);
        add(moveLink);
        add(new Link("cancel") {
            @Override
            public void onClick() {
                PageParameters parameters = new PageParameters();
                parameters.put("id", rootPageId);
                setResponsePage(DisplayPage.class, parameters);
            }
        });
	}


	public int getRootPageId() {
		return rootPageId;
	}


	@Override
	public void pageSelected(IPage page) {
		if(page.getId().equals(getRootPageId())) {
			moveLink.setEnabled(false);
		}
		else {
			moveLink.setEnabled(true);
		}
		selectedPage = page;
	}

	protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }
}

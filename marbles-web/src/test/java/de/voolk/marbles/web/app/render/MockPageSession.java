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
package de.voolk.marbles.web.app.render;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.IPageTraversationHandler;

import java.util.List;

public class MockPageSession implements IPageSession {
    @Override
    public IPage createPage(int parentPageId, String name, String content) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage findPageById(int id) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void updatePage(int id, String content) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage getRootPage() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void removePage(int id) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public IPage findPageByParentAndName(IPage parent, String name) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public List<IPage> getPagePath(IPage page) {
        throw new UnsupportedOperationException("not implemented");
    }

	@Override
	public boolean hasChildren(IPage page) {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void traverse(int pageId, IPageTraversationHandler handler) {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void movePageTo(int sourcePageId, int targetPageId) {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public void renamePage(Integer id, String name) {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public List<IPage> getChildren(int id) {
		throw new UnsupportedOperationException("not implemented");
	}
}

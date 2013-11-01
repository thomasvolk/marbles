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
package de.voolk.marbles.api.pages;

import java.util.List;

import de.voolk.marbles.api.beans.IPage;

public interface IPageSession {
    IPage createPage(int parentPageId, String name, String content);

    IPage findPageById(int id);

    void updatePage(int id, String content);

    IPage getRootPage();

    void removePage(int id);

    IPage findPageByParentAndName(IPage parent, String name);

    List<IPage> getPagePath(IPage page);

    boolean hasChildren(IPage page);

    void traverse(int pageId, IPageTraversationHandler handler);

	void movePageTo(int sourcePageId, int targetPageId);

	void renamePage(Integer id, String name);

	List<IPage> getChildren(int id);
}

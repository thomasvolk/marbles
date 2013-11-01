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
package de.voolk.marbles.persistence.services;

import java.util.List;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.beans.IUser;
import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.persistence.beans.User;

public interface IPageService {
    void updatePage(IUser user, int id, String content);

	IPage findPageByUserAndId(IUser user, int id);

    IPage findPageByUserAndParentAndName(IUser user, IPage parent, String name);

	void removePage(IUser user, int id);

	IPage getRootPage(IUser user);

    IPage createPage(IUser user, int parentPageId, String name, String content);

    List<IPage> getPagePath(IUser user, IPage page);

	void removeAllPages(User user);

	boolean hasChildren(IPage page);

	void traverse(IUser user, int pageId, IPageTraversationHandler handler);

	void movePageTo(User user, int sourcePageId, int targetPageId);

	void renamePage(User user, Integer id, String name);

	List<IPage> getChildren(User user, int id);
}
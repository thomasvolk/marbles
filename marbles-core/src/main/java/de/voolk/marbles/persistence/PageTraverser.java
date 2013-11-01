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
package de.voolk.marbles.persistence;

import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.persistence.beans.Page;

public class PageTraverser {
	private final IPageTraversationHandler traversationHandler;

	public PageTraverser(IPageTraversationHandler pageHandler) {
		this.traversationHandler = pageHandler;
	}

	private IPageTraversationHandler getTraversationHandler() {
		return traversationHandler;
	}

	public void traverse(Page page) {
		traverse(page, null);
	}

	public void traverse(Page page, Page parent) {
		getTraversationHandler().handle(page, parent);
		for(Page child: page.getChildren()) {
			traverse(child, page);
		}
	}
}

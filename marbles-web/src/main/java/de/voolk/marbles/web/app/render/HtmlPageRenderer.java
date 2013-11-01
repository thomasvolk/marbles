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

import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.render.PageRenderer;
import de.voolk.marbles.web.app.IUrlResolver;

public class HtmlPageRenderer extends PageRenderer {
	protected final IUrlResolver urlResolver;
	protected final IPageSession pageSession;

	public HtmlPageRenderer(IPageSession pageSession, IUrlResolver urlResolver) {
		super();
		this.urlResolver = urlResolver;
		this.pageSession = pageSession;
	}

	public IPageSession getPageSession() {
	    return pageSession;
	}

	public IUrlResolver getUrlResolver() {
	    return urlResolver;
	}

}
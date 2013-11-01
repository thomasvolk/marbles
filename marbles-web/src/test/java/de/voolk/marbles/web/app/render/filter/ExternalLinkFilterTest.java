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
package de.voolk.marbles.web.app.render.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import de.voolk.marbles.api.pages.render.filter.IContentFilter;

public class ExternalLinkFilterTest {
	@Test
	public void simple() {
		IContentFilter filter = new ExternalLinkFilter();
		assertEquals("    <a href=\"http://google.de\">http://google.de</a>    ", 
				filter.filter("    http://google.de    "));
	}
	
	@Test
	public void html() {
		IContentFilter filter = new ExternalLinkFilter();
		assertEquals("    <p><a href=\"http://google.de\">http://google.de</a></p>    ", 
				filter.filter("    <p>http://google.de</p>    "));
	}
}

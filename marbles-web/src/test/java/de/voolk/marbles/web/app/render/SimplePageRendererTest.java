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
import de.voolk.marbles.persistence.beans.Page;
import de.voolk.marbles.web.app.IUrlResolver;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimplePageRendererTest {
    @Test
    public void testRender() {
        IPageSession session = new MockPageSession();
        IUrlResolver urlResolver = new MockUrlResolver();
        SimplePageRenderer renderer = new SimplePageRenderer(session, urlResolver);
        String content = "$ 123 $$ $$$ the pre \nsection $$$ hello test \n$$$\n second pre \n$$$\n 123";
        Page page = new Page();
        page.setContent(content);
        assertEquals("$ 123 $$ <pre> the pre \nsection </pre> hello test \n" +
                "<br/>\n<pre>\n second pre \n</pre>\n<br/>\n 123",
                renderer.toHtml(page));
    }
    
    @Test
    public void testExternalLinks() {
        IPageSession session = new MockPageSession();
        IUrlResolver urlResolver = new MockUrlResolver();
        SimplePageRenderer renderer = new SimplePageRenderer(session, urlResolver);
        String content = "http://localhost:1234/bla/test";
        Page page = new Page();
        page.setContent(content);
        assertEquals("<a href=\"http://localhost:1234/bla/test\">http://localhost:1234/bla/test</a>",
                renderer.toHtml(page));
    }
}

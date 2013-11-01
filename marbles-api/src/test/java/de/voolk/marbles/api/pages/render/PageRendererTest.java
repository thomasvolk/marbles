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
package de.voolk.marbles.api.pages.render;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.render.filter.AbstractRegularExpressionFilter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PageRendererTest {
    @Test
    public void testRender() {
        IPageRenderer renderer = new PageRenderer();
        renderer.setLinkResolver(new ILinkResolver() {
            @Override
            public String renderPageLink(IPage parentPage, String name) {
                return String.format("LINK[parent=%s,name=%s]", parentPage.getId(), name);
            }
        });
        renderer.addContentFilter(new AbstractRegularExpressionFilter() {
                @Override
                protected String getExpression() {
                    return "\n";
                }

                @Override
                protected String getReplacement(String original) {
                    return "\n<br/>\n";
                }
            });
        IPage page = new IPage() {

            @Override
            public String getContent() {
                return "Test Page\n%%NewPage Foo Bar\n123456";
            }

            @Override
            public String getName() {
                return "The Parent Page";
            }

            @Override
            public boolean isRoot() {
                return false;
            }

            @Override
            public Integer getId() {
                return 8;
            }

			@Override
			public IPage getParent() {
				return null;
			}
        };
        assertEquals("Test Page\n<br/>\nLINK[parent=8,name=NewPage] Foo Bar\n<br/>\n123456",
                renderer.toHtml(page));
    }
}

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

import de.voolk.marbles.api.pages.render.filter.AbstractRegularExpressionFilter;

public class ExternalLinkFilter extends AbstractRegularExpressionFilter {
	public static final String[] IMAGE_SUFFIXES = {
		".gif", ".jpg", ".jpeg", ".jpe", ".png", ".tif", ".tiff"
	};
	public static boolean hasImageSuffix(String original) {
		for(String suffix: IMAGE_SUFFIXES) {
			if(original.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	} 
	@Override
    protected String getExpression() {
         return "((mailto\\:|(news|(ht|f)tp(s?))\\://){1}[^\\s\\<]+)";
    }

    @Override
    protected String getReplacement(String original) {
     	if(original.startsWith("http") && hasImageSuffix(original)) {
     		return String.format("<img alt=\"%s\" src=\"%s\"/>", original, original);
     	}
     	else {
     		return String.format("<a href=\"%s\">%s</a>", original, original);
     	}
    }
}

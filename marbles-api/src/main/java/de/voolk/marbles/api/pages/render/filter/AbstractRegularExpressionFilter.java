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
package de.voolk.marbles.api.pages.render.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRegularExpressionFilter implements IContentFilter {
    abstract protected String getExpression();
    abstract protected String getReplacement(String original);

    public String filter(String content) {
        Pattern pattern = Pattern.compile(getExpression());
        Matcher matcher = pattern.matcher(content);
        StringBuffer result = new StringBuffer();

        while(matcher.find()) {
            String original = content.substring(matcher.start(), matcher.end());
            String replacement = getReplacement(original);
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
        return result.toString();
    }
}

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
         return "((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)";
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

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

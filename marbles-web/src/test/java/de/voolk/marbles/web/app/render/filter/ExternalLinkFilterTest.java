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

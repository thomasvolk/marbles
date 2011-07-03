package de.voolk.marbles.web.app.render;


import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.persistence.beans.Page;
import de.voolk.marbles.web.app.IUrlResolver;
import org.junit.Test;
import static org.junit.Assert.*;

public class WebPageRendererTest {
    @Test
    public void testRender() {
        IPageSession session = new MockPageSession();
        IUrlResolver urlResolver = new MockUrlResolver();
        WebPageRenderer renderer = new WebPageRenderer(session, urlResolver);
        String content = "$ 123 $$ $$$ the pre \nsection $$$ hello test \n$$$\n second pre \n$$$\n 123";
        Page page = new Page();
        page.setContent(content);
        assertEquals("$ 123 $$ <pre> the pre \nsection </pre> hello test \n" +
                "<br/>\n<pre>\n second pre \n</pre>\n<br/>\n 123",
                renderer.toHtml(page));
    }
}

package de.voolk.marbles.webtest;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

public class MarblesWebTest {
	@Before
    public void prepare() {
        setBaseUrl("http://localhost:9099/marbles");
    }
	 
	@Test
	public void testHomePage() {
		beginAt("/");
        assertLinkNotPresent("logoutLnk");
		assertTitleEquals("Marbles");
		setTextField("username", "test");
        setTextField("password", "test");
        submit();
        assertLinkPresent("logoutLnk");
        clickLink("logoutLnk");
	}
	
}

package de.voolk.marbles.webtest;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkNotPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickElementByXPath;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MarblesWebTest {
	@Before
    public void prepare() {
        setBaseUrl("http://localhost:9099/marbles");
    }
	 
	@Test
	public void testHomePage() {
		/*
		beginAt("/");
        assertLinkNotPresent("logoutLink");
		assertTitleEquals("Marbles");
		setTextField("username", "test");
        setTextField("password", "test");
        submit();
        
        String subPageName = String.format("newSubpage_%s", new Date().getTime());
        
        clickLink("editPageLink");
        setTextField("pageContent", "this test was inserted by the JWebUnitTest \n\n %%" + subPageName);
        submit();
        
        clickElementByXPath("//*[contains(child::text(),'" + subPageName + "')]");
        assertLinkPresent("cancelLink");
        setTextField("pageContent", "this test was inserted by the JWebUnitTest for the subpage");
        submit();
        
        assertLinkPresent("logoutLink");
        clickLink("logoutLink");
        */
	}
	
}

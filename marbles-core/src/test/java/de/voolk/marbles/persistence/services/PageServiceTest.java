package de.voolk.marbles.persistence.services;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.persistence.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/marbles-core/core.xml")
public class PageServiceTest {
	@Autowired
	private IPageService pageService;
	@Autowired
	private IAuthentificationService authentificationService;

    private User getUser() {
        return authentificationService.findUserByName("test");
    }

    @Test
	public void rootPage() {
		pageService.getRootPage(getUser());
	}

    @Test
	public void createPage() {
		IPage rootPage = pageService.getRootPage(getUser());
	    pageService.createPage(getUser(), rootPage.getId(), "My new Page", "the Content");
     }
}

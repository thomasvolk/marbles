package de.voolk.marbles.persistence.services;

import de.voolk.marbles.persistence.utils.AuthDataImporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/marbles-core/core.xml")
public class ApplicationInitialisationServiceTest {
	@Autowired
	private IApplicationInitialisationService applicationInitialisationService;
	
	@Test
	public void initAuth() {
		applicationInitialisationService.importAuthentificationData(
			new AuthDataImporter().parse(
					getClass().getClassLoader().getResourceAsStream("users.xml")).getUsers());
	}
}

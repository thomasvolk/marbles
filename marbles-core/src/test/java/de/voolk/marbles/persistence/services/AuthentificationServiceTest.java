package de.voolk.marbles.persistence.services;

import de.voolk.marbles.persistence.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring/marbles-core/core.xml")
public class AuthentificationServiceTest {
	@Autowired
	private IAuthentificationService authentificationService;
	
	@Test
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void authentification() {
		assertFalse(authentificationService.authenticate("unit-test-01", "123"));
		User user = authentificationService.createUser("unit-test-01", "a@b.com", "123");
		assertFalse(authentificationService.authenticate("unit-test-01", ""));
		assertTrue(authentificationService.authenticate("unit-test-01", "123"));
		authentificationService.removeUser(user.getId());
		assertFalse(authentificationService.authenticate("unit-test-01", "123"));
	}
}

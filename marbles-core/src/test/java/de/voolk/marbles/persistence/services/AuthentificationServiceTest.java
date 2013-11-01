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

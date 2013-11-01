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

import de.voolk.marbles.persistence.beans.Role;
import de.voolk.marbles.persistence.beans.User;

import java.util.Collection;
import java.util.List;

public interface IAuthentificationService {

	List<User> findAllUsers();

	User findUserByName(String name);

    Collection<Role> findRolesForUser(int id);

    void persistUser(User user);

	Role findRoleByName(String name);

	User createUser(String name, String email, String password);

	boolean authenticate(String login, String password);

	User findUserByIdentKey(String identKey);

	User findUserById(int userId);

	void removeUser(int id);

	Role createRole(String name);

	Role findRoleById(int roleId);
	
	boolean userHasRole(User user, String roleName);
	
	void addRolesToUser(int userId, String ...roleName);

	void setRolesToUser(int userId, String ...roleNames);

    void changePassword(int id, String password);
}
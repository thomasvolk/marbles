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
}
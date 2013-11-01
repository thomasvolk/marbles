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
import de.voolk.marbles.utils.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Component
public class AuthentificationService implements IAuthentificationService  {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private Digest passwordDigest;

    private void addRolesToUser(User user, String... roleNames) {
		for(String roleName: roleNames) {
			Role role = findRoleByName(roleName);
			user.getRoles().add(role);
		}
		persistUser(user);
	}
    
    public Digest getPasswordDigest() {
        return passwordDigest;
    }

    @Override
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
        Query query = entityManager.createNamedQuery("user:listAll");
        return query.getResultList();
    }

    @Override
	public User findUserByName(String name) {
        Query query = entityManager.createNamedQuery("user:byName");
        query.setParameter("name", name);
        try {
            return (User) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<Role> findRolesForUser(int id) {
        Set<Role> roles = new HashSet<Role>();
        roles.addAll(findUserById(id).getRoles());
        return roles;
    }

    @Override
	public void persistUser(User user) {
        entityManager.persist(user);
    }
    
    @Override
	public Role findRoleByName(String name) {
        Query query = entityManager.createNamedQuery("role:byName");
        query.setParameter("name", name);
        try {
            return (Role) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
	public User createUser(String name, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        Role userRole = findRoleByName("user");
        user.getRoles().add(userRole);
        user.setPassword(getPasswordDigest().digest(password));
        IdentKey identKey = new IdentKey(user.getName(), user.getPassword());
        user.setIdentKey(identKey.toString());
        persistUser(user);
        return user;
    }

    @Override
	public boolean authenticate(String login, String password) {
        User user = findUserByName(login);
        if (user == null) {
            return false;
        } else {
            String userPassword = user.getPassword();
            if(userPassword == null || userPassword.trim().isEmpty()) {
            	return false;
            }
            else {            	
            	return userPassword.equals(getPasswordDigest().digest(password));
            }
        }
    }

    @Override
	public User findUserByIdentKey(String identKey) {
        Query query = entityManager.createNamedQuery("user:byIdentKey");
        query.setParameter("identKey", identKey);
        try {
            return (User) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
	public User findUserById(int userId) {
		return entityManager.find(User.class, userId);
	}

	@Override
	public Role createRole(String name) {
		Role role = new Role();
		role.setName(name);
		entityManager.persist(role);
		return role;
	}

	@Override
	public Role findRoleById(int roleId) {
		return entityManager.find(Role.class, roleId);
	}

	@Override
	public void addRolesToUser(int userId, String ...roleNames) {
		User user = findUserById(userId);
		addRolesToUser(user, roleNames);
	}
	
	@Override
	public void setRolesToUser(int userId, String ...roleNames) {
		User user = findUserById(userId);
		user.getRoles().clear();
		addRolesToUser(user, roleNames);
	}

    @Override
    public void changePassword(int id, String password) {
    	User user = findUserById(id);
    	user.setPassword(getPasswordDigest().digest(password));
    	persistUser(user);
    }

    @Override
	public boolean userHasRole(User user, String roleName) {
		Collection<Role> roles = findRolesForUser(user.getId());
		for(Role role: roles) {
			if(role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}
	
}

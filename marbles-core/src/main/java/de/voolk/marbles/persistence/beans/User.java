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
package de.voolk.marbles.persistence.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.voolk.marbles.api.beans.IUser;

@NamedQueries(value = {
          @NamedQuery(name = "user:byName", 
        		  query = "select object(u) from User u where name=:name")
        , @NamedQuery(name = "user:listAll", 
        		query = "select object(u) from User u") 
        , @NamedQuery(name = "user:byIdentKey", 
        		query = "select object(u) from User u where identKey=:identKey") 
})
@Entity
@Table(name = "muser")
public class User implements Serializable, IUser {
	private static final long serialVersionUID = 2L;
	
	public final static String SYSTEM_USER = "system";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true, nullable=false)
    private String name;
    private String password;
    private String email;
    @Column(unique = true)
    private String identKey;
    @ManyToMany 
    private Set<Role> roles;
    
    
	public User() {
	}

	public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
     
	public Set<Role> getRoles() {
    	if(roles == null) {
    		roles = new HashSet<Role>();
    	}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

	@Override
    public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
        this.email = email;
    }

	@Override
    public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	@Override
    public String getIdentKey() {
        return identKey;
    }

	public void setIdentKey(String identKey) {
        this.identKey = identKey;
    }
	
	public boolean hasRoleWithName(String roleName) {
		boolean result = false;
		for(Role role: getRoles()) {
			if(role.getName().equals(roleName)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
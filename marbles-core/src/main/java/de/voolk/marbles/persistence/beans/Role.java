package de.voolk.marbles.persistence.beans;

import de.voolk.marbles.api.beans.IIdentifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQueries(value = {
        @NamedQuery(name = "role:byName", 
        		query = "select object(r) from Role r where name=:name")
})
@Entity
@Table(name = "mrole")
public class Role implements Serializable, IIdentifiable {
	private static final long serialVersionUID = 2L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true, nullable=false)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
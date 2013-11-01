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
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import de.voolk.marbles.api.beans.IPage;

@NamedQueries(value = {
        @NamedQuery(name = "page:rootPage",
        		query = "select object(p) from Page p where parent is null and user = :user"),
		@NamedQuery(name = "page:listByUser",
        		query = "select object(p) from Page p where user = :user"),
        @NamedQuery(name = "page:byUserAndId",
        		query = "select object(p) from Page p where id = :id and user = :user"),
        @NamedQuery(name = "page:byUserAndParentAndName",
               query = "select object(p) from Page p where parent = :parent and name = :name and user = :user"),
        @NamedQuery(name = "page:hasChildren",
               query = "select count(p) from Page p where parent = :parent")
})
@Entity
@Table(name = "page", uniqueConstraints=@UniqueConstraint(columnNames={"name", "parent_id"}))
public class Page implements Serializable, IPage {
	private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Lob
    @Column(length=128000)
    private String content;
    @ManyToOne
    private Page parent;
    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="parent")
    private Collection<Page> children;

	@Override
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Collection<Page> getChildren() {
		if(children == null) {
			children = new HashSet<Page>();
		}
		return children;
	}

	public void setChildren(Collection<Page> children) {
		this.children = children;
	}

	public Page getParent() {
		return parent;
	}

	public void setParent(Page parent) {
		this.parent = parent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

	@Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    public void setName(String name) {
        this.name = name;
    }

}
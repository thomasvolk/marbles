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

import de.voolk.marbles.api.beans.IIdentifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractEntityService<T> {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public T findById(Class<? extends T> clazz, int id) {
        return getEntityManager().find(clazz, id);
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    protected <E> E resolve(Class<E> targetCLass, IIdentifiable entity) {
        if(targetCLass.isInstance(entity)) {
             return targetCLass.cast(entity);
        }
        else {
            return getEntityManager().find(targetCLass, entity.getId());
        }
    }
}

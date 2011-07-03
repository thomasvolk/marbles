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

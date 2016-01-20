package ub.misim.feedgenerator.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import ub.misim.feedgenerator.dao.IGenericDao;
import ub.misim.feedgenerator.entities.CommonBaseClass;
import ub.misim.feedgenerator.exception.PersistenceException;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;

	private final Class<T> persistentClass;

	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T insert(T entity) {
		if (entity != null) {
			if (((CommonBaseClass) entity).getGuid() != null) {
				((CommonBaseClass) entity).setGuid(UUID.randomUUID().toString());
			}
			getSession().saveOrUpdate(entity);
		}
		return entity;

	}

	public T update(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public T find(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public List<T> getAll() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public T findByName(String propertyName, String uniqueValue) {

		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propertyName, uniqueValue));

		List list = criteria.list();

		if (list.size() == 0) {
			return null;
		} else if (list.size() == 1) {
			return (T) list.get(0);
		} else {
			throw new PersistenceException(
					"Entity having duplicate for " + propertyName + " field : " + persistentClass);
		}

	}

	public T findByName(String uniqueName) {
		return findByName("name", uniqueName);
	}

}
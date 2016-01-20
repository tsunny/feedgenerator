package ub.misim.feedgenerator.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {

	T insert(T entity);

	T update(T entity);

	void delete(T entity);

	T find(Long PK);

	List<T> getAll();

	T findByName(String propertyName, String uniqueName);

	T findByName(String uniqueName);

}
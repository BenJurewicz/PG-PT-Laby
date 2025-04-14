package jkz.Database.Managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jkz.Database.Misc.Persistable;
import jkz.Database.SqlQuery;
import jkz.Logging.Log;

import java.util.List;

public class Manager<T extends Persistable> {
	static protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	protected Class<T> entityClass;

	public Manager(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void add(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		Log.debug("Added entity to database: ", entity.toString());
	}

	public T find(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		T entity = em.find(entityClass, id);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public void update(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
		Log.debug("Updated entity in database: ", entity.toString());
	}

	public void remove(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		T entity = em.find(entityClass, id);
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
		Log.debug("Removed entity from database: ", entity.toString());
	}

	public List<?> sql(String sqlQuery) {
		SqlQuery s = new SqlQuery(sqlQuery);
		return sql(s).getResult();
	}

	public SqlQuery sql(SqlQuery sqlQuery) {
		try (EntityManager em = emf.createEntityManager()) {
			Query query = em.createNativeQuery(sqlQuery.getQuery());
			SqlQuery queryResult = new SqlQuery(sqlQuery.getQuery(), query.getResultList());
			return queryResult;
		}
	}
}

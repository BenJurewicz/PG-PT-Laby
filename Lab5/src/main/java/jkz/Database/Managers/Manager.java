package jkz.Database.Managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jkz.Database.Misc.Persistable;
import jkz.Logging.Log;

public class Manager<T extends Persistable> {
	protected EntityManagerFactory emf;
	protected Class<T> entityClass;

	public Manager(Class<T> entityClass) {
		this.emf = Persistence.createEntityManagerFactory("PersistenceUnit");
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

	public void find(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		T entity = em.find(entityClass, id);
		em.getTransaction().commit();
		em.close();
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
}

package daw.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.UserTransaction;

public abstract class GenericDAO<T> {

    // Factoría estática para compartirla entre todas las instancias y mejorar rendimiento
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAW_PracticaPU");
    protected EntityManager em;
    private Class<T> entityClass;
    protected UserTransaction utx;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.em = emf.createEntityManager();
        this.utx = getUserTransaction();
    }

    // Obtener transacción para JTA
    private UserTransaction getUserTransaction(){
        return (UserTransaction)ctx.lookup("java:comp/UserTransaction");
    }


    public void create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void edit(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void remove(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}

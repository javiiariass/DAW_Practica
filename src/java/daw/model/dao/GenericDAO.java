package daw.model.dao;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import java.io.Serializable;

// Asegurar que el objeto tenga id
import daw.model.entity.Entitable;
import jdk.jshell.spi.ExecutionControl;

public abstract class GenericDAO<T extends Entitable<Long>> {

    // Factoría estática para compartirla entre todas las instancias y mejorar rendimiento
    // protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAW_PracticaPU");
    @PersistenceContext(unitName = "DAW_PracticaPU")
    protected EntityManager em;

    // Clase que gestiona el DAO
    private Class<T> entityClass;

    // Recurso que inyecta glassfish con la Unidad de persistencia -> "DAW_PracticaPU"
    @Resource
    protected UserTransaction utx;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity){
        // Si el usuario existe, se modifica.
        if (entity.getId() != null) {
            edit(entity);
            return;
        }
        try {
            utx.begin();
            em.persist(entity);
            utx.commit();
        } catch (Exception e) {

            //utx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void edit(T entity) {
        try {
            utx.begin();
            em.merge(entity);
            utx.commit();
        } catch (Exception e) {

            // utx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void remove(T entity) {
        if (entity.getId() == null) {
            return;
        }
        try {
            utx.begin();

            T conectado = em.find(entityClass, entity.getId()); // find puede devolver null si el id existe en el objeto java pero borrado de la BD por otro usuario

            if (conectado != null) {
                em.remove(conectado);
            }

            utx.commit();
        } catch (Exception e) {
            // utx.rollback();
            throw new RuntimeException(e);
        }
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

}

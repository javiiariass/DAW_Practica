/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model.dao;

import daw.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author javi
 */
public class UserDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("DAW_PracticaPU");
        em = emf.createEntityManager();
    }

    public void create(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void edit(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void remove(User user) {
        try {
            em.getTransaction().begin();
            em.remove(em.merge(user));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        Query q = em.createNamedQuery("User.findAll");
        return q.getResultList();
    }
    
    public User findByUsername(String username) {
        try {
            Query q = em.createNamedQuery("User.findByUsername");
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public void close() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}

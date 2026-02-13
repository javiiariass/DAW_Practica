/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model.dao;

import daw.model.entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Query;
import java.util.List;
    
/**
 *
 * @author javi
 */
@RequestScoped
public class UserDAO extends GenericDAO<User> {
    
    public UserDAO() {
        super(User.class);
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author javi
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = " User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    // ******************* Fields *******************
    // PrimaryKey of DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String username; // Deberia ser unico tambien
    String email;
    String passwordHash;
    //Role role; Para asignar rol(admin,user)
    Date createdAt; // Fecha creacion cuenta
    //boolean enabled; // Para gestionar la suspuension de cuentas más adelante

    // ******************* Getters & Setters *******************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }
    public Date getCreatedAt() {
        return createdAt;
    }

//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "daw.model.User[ id=" + id + " ]";
    }

}

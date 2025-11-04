/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model.entity;

import daw.model.valueObject.IngredientValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Javi
 */
@Entity
@Table(name = "recipes")
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
    @NamedQuery(name = "Recipe.findByName", query = "SELECT r FROM Recipe r WHERE r.name = :name")
})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ******************* Fields *******************
    // PrimaryKey of DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String titulo;
    String descripcion;
    String instrucciones;
    int tiempoMinutos;
    int porciones;
    double calorias; //permite ser nulo
    double proteinas; //permite ser nulo
    double grasas; //permite ser nulo
    double hidratos; //permite ser nulo
    String rutaImagen;
    boolean publica;
    Date createdAt;
    Date updatedAt;
    // Objeto externo para facilitar migrar si me da tiempo a implementar APIs para calcular macros
    List<IngredientValue> ingredients;
    
    // ******************* Getters & Setters *******************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "daw.model.Recipe[ id=" + id + " ]";
    }
    
}

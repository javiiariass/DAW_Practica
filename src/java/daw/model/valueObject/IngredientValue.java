/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.model.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author javi
 */
@Embeddable
public class IngredientValue {

    // ******************* Fields *******************
    
    @Column(name="nombre",nullable = false, length = 100)
    private String name;
    
    @Column(name="cantidad_texto", nullable = false, length = 50)
    private String quantityText;
    
    
    // ******************* Ctor *******************
    // Necesario para jpa
    protected IngredientValue() {
    }

    public IngredientValue(String name, String quantityText) {
        this.name = name;
        this.quantityText = quantityText;
    }

    // ******************* Getters && Setters *******************
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantityText() {
        return quantityText;
    }
    public void setQuantityText(String quantityText) {
        this.quantityText = quantityText;
    }

    
    
    // ******************* Override *******************
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, quantityText);
    }

    @Override
    public boolean equals(Object object) {
        // mismo objeto
        if (this == object) return true;
        
        if (!(object instanceof IngredientValue)) {
            return false;
        }
        IngredientValue other = (IngredientValue) object;
        
        return Objects.equals(this.name, other.name) &&
                Objects.equals(this.quantityText, other.quantityText);
    }

    @Override
    public String toString() {
        //return "daw.model.valueObject.IngredientValue[ nombre=" + name + ", " + quantityText + " ]";
        return quantityText + " de " + name;
    }
    
}

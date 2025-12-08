/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daw.model.entity;

/**
 * Todas las entidades que hagan uso de CRUD deben tener id
 * @author javi
 */
public interface Entitable<C> {
    C getId();
    void setId(C id);
}

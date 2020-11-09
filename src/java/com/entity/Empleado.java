/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Juan Pablo PC
 */
@Entity
@PrimaryKeyJoinColumn(name= "id")
public class Empleado extends Usuario implements Serializable {

    @OneToMany(mappedBy = "empleado")
    private List<Cotizacion> cotizacions;

 
    private String nombre;
    private String apellido;
    private String idenficiacion;

   

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the idenficiacion
     */
    public String getIdenficiacion() {
        return idenficiacion;
    }

    /**
     * @param idenficiacion the idenficiacion to set
     */
    public void setIdenficiacion(String idenficiacion) {
        this.idenficiacion = idenficiacion;
    }
    
}

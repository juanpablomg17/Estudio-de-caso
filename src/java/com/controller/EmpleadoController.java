/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Empleado;
import com.services.EmpleadoServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Juan Pablo PC
 */
@ManagedBean
@SessionScoped
public class EmpleadoController {

    private String paginaActual = "";
    
    @ManagedProperty("#{productoController}")
    private ProductoController procon = new ProductoController();
    
    @ManagedProperty("#{cotizacionController}")
    private CotizacionController cotcon = new CotizacionController();
    
    
    
    
    public EmpleadoController() {
    }
    
   
    
    
    public void generarEmpleado(){
        Empleado emp = new Empleado();
        emp.setApellido("Gonzalez");
        emp.setEmail("jairo@gmail.com");
        emp.setEstado("activo");
        emp.setIdenficiacion("23123123");
        emp.setLogin("jairo");
        emp.setNombre("Jairo");
        emp.setPassword("123456");
        emp.setTipo("Empleado");
        EmpleadoServices empser = new EmpleadoServices();
        empser.crear(emp);
    }
    
    

    /**
     * @return the paginaActual
     */
    public String getPaginaActual() {
        return paginaActual;
    }

    /**
     * @param paginaActual the paginaActual to set
     */
    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }
    
    public void gestionarProductos(){
         setPaginaActual("/Vistas/Empleado/GestorProductos.xhtml");
         
    }
    
    public void gestionarCotizaciones(){
        paginaActual="/Vistas/Empleado/GestorCotizaciones.xhtml";
    }

    /**
     * @return the procon
     */
    public ProductoController getProcon() {
        return procon;
    }

    /**
     * @param procon the procon to set
     */
    public void setProcon(ProductoController procon) {
        this.procon = procon;
    }

    /**
     * @return the cotcon
     */
    public CotizacionController getCotcon() {
        return cotcon;
    }

    /**
     * @param cotcon the cotcon to set
     */
    public void setCotcon(CotizacionController cotcon) {
        this.cotcon = cotcon;
    }
}

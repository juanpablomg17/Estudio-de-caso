/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.services.ClienteServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Juan Pablo PC
 */
@ManagedBean
@SessionScoped
public class ClienteController {
    
    private Cliente cliente = new Cliente();
    
    private ClienteServices cliser = new ClienteServices();
    
    private String paginaActual;
    
    @ManagedProperty("#{productoController}")
    private ProductoController procon = new ProductoController();
    @ManagedProperty("#{cotizacionController}")
    private CotizacionController cotcon = new CotizacionController();
    
    
    public ClienteController() {
    }
    
    public void registrar(){
        getCliente().setEstado("Activo");
        getCliente().setTipo("Cliente");
        getCliser().crear(getCliente());
        setCliente(new Cliente());
    }
    
    public void verProductos(){
        setPaginaActual("/Vistas/Cliente/VitrinaProductos.xhtml");
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the cliser
     */
    public ClienteServices getCliser() {
        return cliser;
    }

    /**
     * @param cliser the cliser to set
     */
    public void setCliser(ClienteServices cliser) {
        this.cliser = cliser;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.entity.Empleado;
import com.entity.Usuario;
import com.services.ClienteServices;
import com.services.EmpleadoServices;
import com.services.UsuarioServices;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jcmm80
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable{

    /**
     * @return the ususer
     */
    public UsuarioServices getUsuser() {
        return ususer;
    }

    /**
     * @param ususer the ususer to set
     */
    public void setUsuser(UsuarioServices ususer) {
        this.ususer = ususer;
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
     * @return the empser
     */
    public EmpleadoServices getEmpser() {
        return empser;
    }

    /**
     * @param empser the empser to set
     */
    public void setEmpser(EmpleadoServices empser) {
        this.empser = empser;
    }

    private Usuario usuario=new Usuario();
    private Empleado empleado=new Empleado();
    private Cliente cliente=new Cliente();
    private boolean mostpanel=false;
    private String paginaActual="";
    
    
    private UsuarioServices ususer = new UsuarioServices();
    private ClienteServices cliser=new ClienteServices();
    private EmpleadoServices empser=new EmpleadoServices();
    
    @ManagedProperty("#{empleadoController}")
    private EmpleadoController empcon=new EmpleadoController();
    @ManagedProperty("#{clienteController}")
    private ClienteController clicon=new ClienteController();
   
    
    public UsuarioController() {
    }
    
    public void cerrar(){
        setUsuario(new Usuario());
        setEmpleado(null);
        setCliente(null);
        setMostpanel(false);
    }
    
    public void iniciar(){
        setUsuario(getUsuser().ingresar(getUsuario().getLogin(), getUsuario().getPassword()));        
        if(getUsuario().getId()!=null){
            setMostpanel(true);
            if(getUsuario().getTipo().equals("Cliente")){
                setCliente(getCliser().consultar(Cliente.class, getUsuario().getId()));
                getClicon().getProcon().obtenerProductos();
                clicon.getCotcon().setCliente(cliente);
                setPaginaActual("GUICliente.xhtml");
            }
            if(getUsuario().getTipo().equals("Empleado")){
                setEmpleado(getEmpser().consultar(Empleado.class, getUsuario().getId()));
                getEmpcon().getProcon().obtenerProductos();
                empcon.getCotcon().obtenerCotizacionesSolicitadas();
                setPaginaActual("GUIEmpleado.xhtml");
            }
        }else{
            setMostpanel(false);
            FacesUtil.addErrorMessage("El usuario no existe");
        }
        
        
        
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the mostpanel
     */
    public boolean isMostpanel() {
        return mostpanel;
    }

    /**
     * @param mostpanel the mostpanel to set
     */
    public void setMostpanel(boolean mostpanel) {
        this.mostpanel = mostpanel;
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
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
     * @return the empcon
     */
    public EmpleadoController getEmpcon() {
        return empcon;
    }

    /**
     * @param empcon the empcon to set
     */
    public void setEmpcon(EmpleadoController empcon) {
        this.empcon = empcon;
    }

    /**
     * @return the clicon
     */
    public ClienteController getClicon() {
        return clicon;
    }

    /**
     * @param clicon the clicon to set
     */
    public void setClicon(ClienteController clicon) {
        this.clicon = clicon;
    }
    
}

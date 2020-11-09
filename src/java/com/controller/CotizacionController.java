/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Cliente;
import com.entity.Cotizacion;
import com.entity.DetalleCotizacion;
import com.entity.Producto;
import com.services.CotizacionServices;
import com.services.DetalleCotizacionServices;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Juan Pablo PC
 */
@ManagedBean
@SessionScoped
public class CotizacionController {

    private Cliente cliente = new Cliente();
    private List<DetalleCotizacion> detalles = new LinkedList();
    private Cotizacion cotizacion = new Cotizacion();
    private List<Cotizacion> cotizacionesS = new LinkedList();
    private List<Cotizacion> cotizacionesA = new LinkedList();
    

    // servicios
    CotizacionServices cotser = new CotizacionServices();
    DetalleCotizacionServices detser = new DetalleCotizacionServices();
    

    public CotizacionController() {

    }
    
    public void obtenerCotizacionesSolicitadas(){
        cotizacionesS=cotser.cotizacionesSolicitadas();
        
    }
    
   public void obtenerDetalles(Cotizacion c){
       detalles = detser.detallesCotizacion(c);
       cotizacion=c;
       
   }
   public void aprobar(){
       if (cotizacion!= null){
           cotizacion.setEstado("Aprobada");
       cotizacion = cotser.modificar(cotizacion);
       FacesUtil.addInfoMessage("La cotización ha sido aprobada");
       }else {
           FacesUtil.addErrorMessage("Error :v");
       }
       
   }

    public void almacenarCotizacion() {
        if (detalles.size()>0){
        cotizacion.setCliente(cliente);
        cotizacion.setEstado("Solicitado");
        cotizacion.setFecha(new Date());
        cotizacion = cotser.modificar(cotizacion);
        for (DetalleCotizacion det : detalles) {
            if (det.getPropuesta()>0){
            det.setCotizacion(cotizacion);
            detser.modificar(det);
                
            }else{
                 FacesUtil.addErrorMessage("La propuesta debe ser mayor que cero");
                 break; // HAY UN BUG
            }
            
        }
        cotizacion = new Cotizacion();
        detalles = new LinkedList();
            
        }else{
            FacesUtil.addErrorMessage("No hay detalles de cotización");
        }
      
    }

    public void agregarProducto(Producto p) {
        DetalleCotizacion dc = new DetalleCotizacion();
        dc.setProducto(p);
        if (!existeProducto(p)) {
            detalles.add(dc);
        } else {
            FacesUtil.addWarnMessage("El producto ya esta seleccionado");
        }
    }

    public void quitar(DetalleCotizacion dc) {
        detalles.remove(dc);

    }

    public void calcularvalores(DetalleCotizacion dc) {
        dc.subtotal();
    }

    public boolean existeProducto(Producto p) {
        boolean exixte = false;
        for (DetalleCotizacion det : detalles) {
            if (det.getProducto().getId().equals(p.getId())) {
                exixte = true;
                break;
            }
        }
        return exixte;
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
     * @return the detalles
     */
    public List<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleCotizacion> detalles) {
        this.detalles = detalles;
    }

    /**
     * @return the cotizacion
     */
    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    /**
     * @param cotizacion the cotizacion to set
     */
    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    /**
     * @return the cotizacionesS
     */
    public List<Cotizacion> getCotizacionesS() {
        return cotizacionesS;
    }

    /**
     * @param cotizacionesS the cotizacionesS to set
     */
    public void setCotizacionesS(List<Cotizacion> cotizacionesS) {
        this.cotizacionesS = cotizacionesS;
    }

    /**
     * @return the cotizacionesA
     */
    public List<Cotizacion> getCotizacionesA() {
        return cotizacionesA;
    }

    /**
     * @param cotizacionesA the cotizacionesA to set
     */
    public void setCotizacionesA(List<Cotizacion> cotizacionesA) {
        this.cotizacionesA = cotizacionesA;
    }

    /**
     * @return the cotizaciones
     */


    /**
     * @return the producto
     */
}

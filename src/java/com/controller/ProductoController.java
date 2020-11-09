/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Empleado;
import com.entity.Producto;
import com.services.EmpleadoServices;
import com.services.ProductoServices;
import com.utilidades.ImageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author jcmm80
 */
@ManagedBean
@SessionScoped
public class ProductoController implements Serializable{

    //entidades de negocio
    private Producto producto = new Producto();
    private UploadedFile filep;
    private Producto proImg = new Producto();

    //servicios
    ProductoServices proser = new ProductoServices();

    //colecciones
    private List<Producto> productos = new LinkedList();

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
        obtenerProductos();

    }
    
    public void upload() {
        if (filep != null) {
            try {
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String path = servletContext.getRealPath("/imag.png");
                path=path.replace("imag.png", "Imagenes\\Productos\\");
                System.out.println(path);
                ImageUtils.copyFile(producto.getId()+".png", filep.getInputStream(), path);
                obtenerProductos();
            } catch (IOException ex) {                
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            FacesUtil.addErrorMessage("no hay archivo seleccionado");
        }
    }
    
    public void consultar(Producto p) {
        producto = p;
    }
    
   
     public void consultarProductoDB(Producto p){
         proImg = producto = proser.consultarProducto(p.getId());
         
     }

    public void deshabilitar(Producto p) {
        p.setEstado("Inactivo");
        p = proser.modificar(p);
        obtenerProductos();
    }

    public boolean validarProducto() {
        boolean valida = true;
        if (producto.getNombre().equals("")) {
            valida = false;
        }
        return valida;
    }

    public void nuevoProducto(){
        producto = new Producto();
    }
    
    public void registrar() {
        producto.setEstado("Activo");
        if (validarProducto()) {
            proser.crear(producto);
            producto = new Producto();
            obtenerProductos();
            
        }
    }
    
    public void eliminar(Producto p){
        proser.eliminar(p);
        obtenerProductos();
    }

    public void obtenerProductos() {
        System.out.println("consultare los productos");
        productos = proser.consultarTodo(Producto.class);
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * @return the filep
     */
    public UploadedFile getFilep() {
        return filep;
    }

    /**
     * @param filep the filep to set
     */
    public void setFilep(UploadedFile filep) {
        this.filep = filep;
    }

    /**
     * @return the proImg
     */
    public Producto getProImg() {
        return proImg;
    }

    /**
     * @param proImg the proImg to set
     */
    public void setProImg(Producto proImg) {
        this.proImg = proImg;
    }

}

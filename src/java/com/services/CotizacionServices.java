/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.controller.FacesUtil;
import com.dao.ImplDao;
import static com.dao.ImplDao.getEntityManagger;
import com.entity.Cotizacion;
import com.entity.Producto;
import com.implDao.ICotizacion;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Juan Pablo PC
 */
public class CotizacionServices extends ImplDao<Cotizacion, Long> implements ICotizacion, Serializable{
    
     public boolean consultarNumeroCotizacion(long id) {
        boolean existe = false;
        EntityManager em = ImplDao.getEntityManagger();

        Cotizacion cotizacion = new Cotizacion();

        em.getTransaction().begin();

        try {
            System.out.println(id);

            String q = "select c from Cotizacion c where c.numero <> ?1";//Se cosulta el número de las cotización

            Query qu = em.createQuery(q)
                    .setParameter(1, id);

            cotizacion = (Cotizacion) qu.getSingleResult();//Guarda un objeto de tipo cotización que captó la consulta  

            if (cotizacion.getId() != null){
                existe = true;
            }else{
                existe = false;
            }
            
   
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Error de consulta de cotización", ex.getMessage());
            existe = false;

        } finally {
            existe = false;
            em.close();

        }
        return existe;//Retorna la variable booleana

    }
     
       public List<Cotizacion> cotizacionesSolicitadas(){
        List<Cotizacion> cotizaciones=new LinkedList();
        EntityManager em =getEntityManagger();
        //System.out.println(l+" y contraceña: "+c);
        em.getTransaction().begin();        
        String q="select c from Cotizacion c where c.estado=?1";        
        System.out.println(" Consulta: "+q);
        Query qu=em.createQuery(q)
                .setParameter(1,"Solicitado");
        cotizaciones=qu.getResultList();
        em.close();   
        return cotizaciones;   
    }
    
}

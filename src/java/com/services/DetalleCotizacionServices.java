/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import static com.dao.ImplDao.getEntityManagger;
import com.entity.Cotizacion;
import com.entity.DetalleCotizacion;
import com.implDao.IDetalleCotizacion;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Juan Pablo PC
 */
public class DetalleCotizacionServices extends ImplDao<DetalleCotizacion, Long> implements IDetalleCotizacion, Serializable{
    
     public List<DetalleCotizacion> detallesCotizacion(Cotizacion c){
        List<DetalleCotizacion> detalles=new LinkedList();
        EntityManager em =getEntityManagger();
        //System.out.println(l+" y contraceña: "+c);
        em.getTransaction().begin();        
        String q="select d from DetalleCotizacion d where d.cotizacion.id=?1";        
        System.out.println(" Consulta: "+q);
        Query qu=em.createQuery(q)
                .setParameter(1,c.getId());
        detalles=qu.getResultList();
        em.close();   
        return detalles;   
    }
}

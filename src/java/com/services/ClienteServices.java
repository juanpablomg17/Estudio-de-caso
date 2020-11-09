/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Cliente;
import com.implDao.ICliente;
import java.io.Serializable;

/**
 *
 * @author Juan Pablo PC
 */
public class ClienteServices extends ImplDao<Cliente, Long> implements ICliente, Serializable{
    
    
}

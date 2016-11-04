/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author lvalbuena
 */
@Local
public interface IPersistenciaBMTLocal {
    
    /**
     * El usuario realiza una compra con pago con tarjeta de credito
     * @param usuario
     * @param inventario
     * @throws CupoInsuficienteException 
     */
    void comprar(Usuario usuario, ArrayList<Mueble> inventario, double precioTotalInventario) 
            throws CupoInsuficienteException;
    
    /**
     * 
     * @param credito
     * @param precioTotalInventario 
     */
    public void descontarCupoTarjeta(long documento, 
            double precioTotalInventario );
    
}

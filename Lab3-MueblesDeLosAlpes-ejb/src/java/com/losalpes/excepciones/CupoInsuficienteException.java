/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.excepciones;

import javax.ejb.ApplicationException;

/**
 * Excepcion qie se lanza cuando la tarjeta de credito no tiene cupo para la compra
 * @author lvalbuena
 */
@ApplicationException(rollback = true)
public class CupoInsuficienteException extends Exception {
    
    
    private String mensaje;
    
    /**
     * Constructor
     * @param mensaje 
     */
    public CupoInsuficienteException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}

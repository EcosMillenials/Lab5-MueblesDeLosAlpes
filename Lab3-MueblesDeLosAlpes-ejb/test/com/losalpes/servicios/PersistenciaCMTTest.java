/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.TipoMueble;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lvalbuena
 */
public class PersistenciaCMTTest extends TestCase {
    
    private Usuario usuario;
    private ArrayList<Mueble> inventario;
    
    public PersistenciaCMTTest() {
        
    }
    
    @Before
    @Override
    public void setUp() {
        inventario = new ArrayList<Mueble>();
        inventario.add(new Mueble(1L, "Silla clásica", "Una confortable silla con estilo del siglo XIX.", TipoMueble.Interior, 45, "", 123));
        inventario.add(new Mueble(2L, "Silla moderna", "Lo último en la moda de interiores. Esta silla le brindará la comodidad e innovación que busca", TipoMueble.Interior, 50, "", 5464));
        inventario.add(new Mueble(3L, "Mesa de jardín", "Una bella mesa para comidas y reuniones al aire libre.", TipoMueble.Exterior, 100, "", 4568));
        
        usuario = new Usuario("cliente", 80221940);

    }
    
    /**
     * test para el metodo comprar
     */
    @Test
    public void testComprar() {
        
        try {
            PersistenciaCMT istancia = new PersistenciaCMT();
            istancia.comprar(usuario, inventario, 94594594);
            assertTrue("Compra Correcta", true);
            
        } catch (CupoInsuficienteException ex) {
            assertTrue("Cupo insuficiente", false);
        }
    }
}

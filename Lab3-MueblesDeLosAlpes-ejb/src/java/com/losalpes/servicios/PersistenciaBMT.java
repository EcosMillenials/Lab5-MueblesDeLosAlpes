/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Tarjetacreditoalpes;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.CupoInsuficienteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author lvalbuena
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PersistenciaBMT implements IPersistenciaBMTLocal {
    
    /**
     * instancia UserTransaction
     */
    @Resource
    private UserTransaction userTransaction;

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    /**
     * Entidad encargada de persistir en la base de datos Derby
     */
    @PersistenceContext(unitName = "Lab5-MueblesDeLosAlpes-Derby")
    private EntityManager entityDerby;
     
     

    /**
     * 
     * @param usuario
     * @throws CupoInsuficienteException 
     */
    @Override
    public void comprar(Usuario usuario, ArrayList<Mueble> inventario, double precioTotalInventario) 
            throws CupoInsuficienteException {
        try {
            userTransaction.begin();
            
            this.registrarVenta(usuario, inventario);
            this.validarCupoTarjeta(usuario, precioTotalInventario);
            
            userTransaction.commit();       
        
    }   catch (RollbackException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(PersistenciaBMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    /**
     * Resgistra los datos de la venta
     * @param usuario
     * @param inventario 
     */
    private void registrarVenta(Usuario usuario, ArrayList<Mueble> inventario) {
        Mueble mueble;
        for (int i = 0; i < inventario.size(); i++)
        {
            mueble = inventario.get(i);
            Mueble editar=(Mueble) persistencia.findById(Mueble.class, mueble.getReferencia());
            editar.setCantidad(editar.getCantidad() - mueble.getCantidad());
            RegistroVenta compra = new RegistroVenta(new Date(System.currentTimeMillis()), 
                    mueble, mueble.getCantidad(), null, usuario);
            usuario.agregarRegistro(compra);

            persistencia.update(usuario);
            persistencia.update(editar);
        }
    }
    
    /**
     * descuenta ell cupo de la tarjeta de credito, si el cupo no alcanza
     * se genera exception
     * @param usuario
     * @throws CupoInsuficienteException 
     */
    private void validarCupoTarjeta(Usuario usuario, double precioTotalInventario) 
            throws CupoInsuficienteException, IllegalStateException, SecurityException, SystemException {
        
        Tarjetacreditoalpes credito = (Tarjetacreditoalpes)entityDerby.createNamedQuery(
                "Tarjetacreditoalpes.findByDocumentoTitular")
                .setParameter("documentoTitular", new Double(usuario.getDocumento())).getSingleResult();
        
        //se valida el cupo de la tarjeta 
        if (credito.getCupo() < precioTotalInventario) {
            userTransaction.rollback();
            throw new CupoInsuficienteException("Cupo Insufuciente para realizar la compra");   
        }
    }
    
    /**
     * descuenta el cupo de la tarjeta de credito de la tarjeta
     * @param precioTotalInventario 
     */
    public void descontarCupoTarjeta(long documento, 
            double precioTotalInventario ) {
        
        Tarjetacreditoalpes credito =  (Tarjetacreditoalpes)entityDerby.createNamedQuery(
                "Tarjetacreditoalpes.findByDocumentoTitular")
                .setParameter("documentoTitular", new Double(documento)).getSingleResult();
                    
            credito.setCupo(credito.getCupo() - precioTotalInventario);
            entityDerby.merge(credito);
    }
}

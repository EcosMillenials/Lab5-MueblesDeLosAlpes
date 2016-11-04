/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ RegistroVenta.java
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenieria de Sistemas y Computacion
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles los Alpes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que modela un registro de venta realizado por un cliente
 * 
 */
@Entity
@NamedQuery(name="RegistroVenta.historialCompras", query="SELECT t FROM RegistroVenta t WHERE t.comprador.nombreCompleto = :nombre")
public class RegistroVenta implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Fecha en la que se vendió el producto
     */
    @Temporal(TemporalType.DATE)
    @Id
    private Date fechaVenta;

    /**
     * Producto vendido
     */
    @ManyToOne
    @JoinColumn(name="referencia")
    @Id
    private Mueble producto;
    
    /**
     * Usuario que compró el producto
     */
    @ManyToOne
    @JoinColumn(name="comprador")
    @Id
    private Usuario comprador;

    

    /**
     * Cantidad vendida del producto
     */
    private int cantidad;

    /**
     * Ciudad en la que se vendió el producto
     */
    private String ciudad;

    

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor sin argumentos
     */
    public RegistroVenta()
    {
        
    }

    /**
     * Constructor de la clase con argumentos
     * @param fechaVenta Fecha en que se realizó la venta
     * @param producto Mueble adquirido
     * @param cantidad Cantidad adquirida
     * @param ciudad Ciudad en la que se vendió el producto
     * @param comprador Usuario que compro el mueble
     */
    public RegistroVenta(Date fechaVenta, Mueble producto, int cantidad,
            String ciudad, Usuario comprador)
    {
        this.fechaVenta = fechaVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.ciudad = ciudad; 
        this.comprador = comprador;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve la cantidad de producto vendido
     * @return cantidad Cantidad de producto vendido
     */
    public int getCantidad()
    {
        return cantidad;
    }

    /**
     * Modifica la cantidad de muebles adquiridos
     * @param cantidad Nueva cantidad de muebles
     */
    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve la ciudad en dónde se realizó la venta
     * @return ciudad Ciudad
     */
    public String getCiudad()
    {
        return ciudad;
    }

    /**
     * Modifica la ciudad dónde se realizó la venta
     * @param ciudad Nueva ciudad
     */
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Mueble getProducto() {
        return producto;
    }

    public void setProducto(Mueble producto) {
        this.producto = producto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    

}

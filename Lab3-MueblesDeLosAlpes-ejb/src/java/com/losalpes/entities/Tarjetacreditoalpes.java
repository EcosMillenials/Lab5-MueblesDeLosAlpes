/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lvalbuena
 */
@Entity
@Table(name = "TARJETACREDITOALPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjetacreditoalpes.findAll", query = "SELECT t FROM Tarjetacreditoalpes t")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByNumeroTarjeta", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.numeroTarjeta = :numeroTarjeta")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByDocumentoTitular", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.documentoTitular = :documentoTitular")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByNombreTitular", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.nombreTitular = :nombreTitular")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByBanco", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.banco = :banco")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByCupo", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.cupo = :cupo")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByFechaExpedicion", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.fechaExpedicion = :fechaExpedicion")
    , @NamedQuery(name = "Tarjetacreditoalpes.findByFechaVencimiento", query = "SELECT t FROM Tarjetacreditoalpes t WHERE t.fechaVencimiento = :fechaVencimiento")})
public class Tarjetacreditoalpes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_TARJETA")
    private Integer numeroTarjeta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DOCUMENTO_TITULAR")
    private Double documentoTitular;
    @Size(max = 50)
    @Column(name = "NOMBRE_TITULAR")
    private String nombreTitular;
    @Size(max = 50)
    @Column(name = "BANCO")
    private String banco;
    @Column(name = "CUPO")
    private Double cupo;
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    public Tarjetacreditoalpes() {
    }

    public Tarjetacreditoalpes(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Double getDocumentoTitular() {
        return documentoTitular;
    }

    public void setDocumentoTitular(Double documentoTitular) {
        this.documentoTitular = documentoTitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Double getCupo() {
        return cupo;
    }

    public void setCupo(Double cupo) {
        this.cupo = cupo;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroTarjeta != null ? numeroTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjetacreditoalpes)) {
            return false;
        }
        Tarjetacreditoalpes other = (Tarjetacreditoalpes) object;
        if ((this.numeroTarjeta == null && other.numeroTarjeta != null) || (this.numeroTarjeta != null && !this.numeroTarjeta.equals(other.numeroTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.losalpes.entities.Tarjetacreditoalpes[ numeroTarjeta=" + numeroTarjeta + " ]";
    }
    
}

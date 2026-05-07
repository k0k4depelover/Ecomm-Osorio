package com.portal.compras.osorio_ecomm.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {
    @Id
    private Long id_producto;
    private String nombre;
    private String descripcion_corta;
    private String descripcion_larga;
    private Float precio_venta;
    private Float stock;
    private Float stock_minimo;
    private String codigo_barras;
    private Float peso_kilogramos;
    private String active;
    private Date fecha_creacion;
    public Long getId_producto() {
        return id_producto;
    }
    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion_corta() {
        return descripcion_corta;
    }
    public void setDescripcion_corta(String descripcion_corta) {
        this.descripcion_corta = descripcion_corta;
    }
    public String getDescripcion_larga() {
        return descripcion_larga;
    }
    public void setDescripcion_larga(String descripcion_larga) {
        this.descripcion_larga = descripcion_larga;
    }
    public Float getPrecio_venta() {
        return precio_venta;
    }
    public void setPrecio_venta(Float precio_venta) {
        this.precio_venta = precio_venta;
    }
    public Float getStock() {
        return stock;
    }
    public void setStock(Float stock) {
        this.stock = stock;
    }
    public Float getStock_minimo() {
        return stock_minimo;
    }
    public void setStock_minimo(Float stock_minimo) {
        this.stock_minimo = stock_minimo;
    }
    public String getCodigo_barras() {
        return codigo_barras;
    }
    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }
    public Float getPeso_kilogramos() {
        return peso_kilogramos;
    }
    public void setPeso_kilogramos(Float peso_kilogramos) {
        this.peso_kilogramos = peso_kilogramos;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}

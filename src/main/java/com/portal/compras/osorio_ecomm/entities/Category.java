package com.portal.compras.osorio_ecomm.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nombre;
    private String descripcion;
    private Long orden;
    private String active;

    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    // Autoreferencia - esta categoria pertenece a una categoria padre
    @ManyToOne
    @JoinColumn(name = "CATEGORIA_id_categoria")
    private Category categoriaPadre;

    // Autoreferencia - esta categoria tiene subcategorias hijas
    @OneToMany(mappedBy = "categoriaPadre")
    private List<Category> subcategorias;

    public Long getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Long getOrden() {
        return orden;
    }
    public void setOrden(Long orden) {
        this.orden = orden;
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
    public Category getCategoriaPadre() {
        return categoriaPadre;
    }
    public void setCategoriaPadre(Category categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
    public List<Category> getSubcategorias() {
        return subcategorias;
    }
    public void setSubcategorias(List<Category> subcategorias) {
        this.subcategorias = subcategorias;
    }
}

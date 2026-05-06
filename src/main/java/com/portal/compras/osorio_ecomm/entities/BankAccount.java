package com.portal.compras.osorio_ecomm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="SIMULADOR_BANCO")
public class BankAccount {
    @Id
    private Long id_usuario;
    private String numero_tarjeta;
    private String nombre_titular;
    private Integer mes_vencimiento;
    private String cvv;
    private Float saldo_disponible;
    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }
    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }
    public String getNombre_titular() {
        return nombre_titular;
    }
    public void setNombre_titular(String nombre_titular) {
        this.nombre_titular = nombre_titular;
    }
    public Integer getMes_vencimiento() {
        return mes_vencimiento;
    }
    public void setMes_vencimiento(Integer mes_vencimiento) {
        this.mes_vencimiento = mes_vencimiento;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public Float getSaldo_disponible() {
        return saldo_disponible;
    }
    public void setSaldo_disponible(Float saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }

}

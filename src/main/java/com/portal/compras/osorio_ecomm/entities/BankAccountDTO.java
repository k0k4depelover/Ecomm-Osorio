package com.portal.compras.osorio_ecomm.entities;

public class BankAccountDTO {
    private Long id_usuario;
    private String nombre_titular;
    private String numero_cuenta;
    private Float saldo_disponible;
    public Long getId_usuario() {
        return id_usuario;
    }
    public String getNumero_cuenta() {
        return numero_cuenta;
    }
    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNombre_titular() {
        return nombre_titular;
    }
    public void setNombre_titular(String nombre_titular) {
        this.nombre_titular = nombre_titular;
    }
    public Float getSaldo_disponible() {
        return saldo_disponible;
    }
    public void setSaldo_disponible(Float saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }
    public BankAccountDTO(Long id_usuario, String nombre_titular, String numero_cuenta, Float saldo_disponible) {
        this.id_usuario = id_usuario;
        this.nombre_titular = nombre_titular;
        this.numero_cuenta = numero_cuenta;
        this.saldo_disponible = saldo_disponible;
    }

}

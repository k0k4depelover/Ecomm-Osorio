package com.portal.compras.osorio_ecomm.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.portal.compras.osorio_ecomm.entities.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    List<Cliente> findAllActiveYN(String active);
    Optional<Cliente> delete(Long id);
    Optional<Cliente> recover(Long id);
    Cliente save(Cliente cliente);
    void InsertClienteEncrypt(String nombre,
        String apellido, String email,
        String password, String telefono, Date fecha_nacimiento,
         String sexo);
    void UpdateClienteEncrypt(Long id, String nombre, String apellido, String email,
        String password, String telefono, Date fecha_nacimiento,
         String sexo);
}

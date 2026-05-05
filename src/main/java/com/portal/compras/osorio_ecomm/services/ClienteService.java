package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import com.portal.compras.osorio_ecomm.entities.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    Optional<Cliente> update(Long id, Cliente cliente);
}

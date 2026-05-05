package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.compras.osorio_ecomm.entities.Cliente;
import com.portal.compras.osorio_ecomm.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) repository.findAll();
    }


    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }


    @Override
    @Transactional
    public Optional<Cliente> update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional= repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente clienteDb= clienteOptional.orElseThrow();
            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setApellido(cliente.getApellido());
            clienteDb.setEmail(cliente.getEmail());
            clienteDb.setPassword(cliente.getPassword());
            clienteDb.setTelefono(cliente.getTelefono());
            clienteDb.setSexo(cliente.getSexo());
            return Optional.of(repository.save(clienteDb));
        }
        return clienteOptional;
    }




}

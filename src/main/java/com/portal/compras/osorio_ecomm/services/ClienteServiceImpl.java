package com.portal.compras.osorio_ecomm.services;

import java.util.Date;
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
    public void UpdateClienteEncrypt(Long id, String nombre, String apellido, String email,
        String password, String telefono, Date fecha_nacimiento,
         String sexo) {
            repository.sp_update_cliente_encrypt(id, nombre, apellido, email,
                password, telefono, fecha_nacimiento, sexo);

    }


    @Override
    @Transactional
    public void InsertClienteEncrypt(String nombre,
        String apellido, String email, String password,
        String telefono,Date fecha_nacimiento, String sexo) {
        repository.sp_insert_cliente_encrypt(nombre,
            apellido, email, password, telefono,
            fecha_nacimiento, sexo);
}


    @Override
    public List<Cliente> findAllActiveYN(String active) {
    if (active == null || active.isEmpty() ||
        (!active.trim().equals("Y") && !active.trim().equals("N"))) {
        throw new IllegalArgumentException("El campo active solo acepta 'Y' o 'N'");
    }
    return repository.findAllByActiveYN(active.trim());
}


    @Override
    @Transactional
    public Optional<Cliente> delete(Long id) {
        Optional<Cliente> clienteOpional = repository.findById(id);
        if (clienteOpional.isPresent()){
            Cliente clienteDb = clienteOpional.orElseThrow();
            clienteDb.setActive("N");
            return Optional.of(repository.save(clienteDb));
        }
        return clienteOpional;

    }

    @Override
    @Transactional
    public Optional<Cliente> recover(Long id) {
        Optional<Cliente> clienteOpional = repository.findById(id);
        if (clienteOpional.isPresent()){
            Cliente clienteDb = clienteOpional.orElseThrow();
            clienteDb.setActive("Y");
            return Optional.of(repository.save(clienteDb));
        }
        return clienteOpional;

    }



}

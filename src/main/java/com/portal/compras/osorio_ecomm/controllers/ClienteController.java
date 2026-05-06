package com.portal.compras.osorio_ecomm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.compras.osorio_ecomm.entities.Cliente;
import com.portal.compras.osorio_ecomm.services.ClienteService;




@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @GetMapping
    public List<Cliente> getAll(){
        return service.findAll();
    }

    @GetMapping("/active/{active}")
    public List<Cliente> findAllActiveYN(@PathVariable String active){
        return service.findAllActiveYN(active);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = service.findById(id);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());

        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));
    }


    @PostMapping("/encrypt")
    public ResponseEntity<?> saveEncrypt(@RequestBody Cliente cliente) {
        service.InsertClienteEncrypt(
        cliente.getNombre(),
        cliente.getApellido(),
        cliente.getEmail(),
        cliente.getPassword(),
        cliente.getTelefono(),
        cliente.getFecha_nacimiento(),
        cliente.getSexo()
    );
        return ResponseEntity.status(HttpStatus.CREATED).body("Creado correctamente.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, cliente));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(id));
    }

    @PutMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.recover(id));
    }


}

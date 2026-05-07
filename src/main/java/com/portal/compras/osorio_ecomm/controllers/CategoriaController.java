package com.portal.compras.osorio_ecomm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.compras.osorio_ecomm.entities.Categoria;
import com.portal.compras.osorio_ecomm.entities.Producto;
import com.portal.compras.osorio_ecomm.services.CategoriaService;




@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping("/{nombre_categoria}")
    public List<Producto> encontrarProductosCategorias(@PathVariable String nombre_categoria) {
        return service.findByCategoriaNombre(nombre_categoria);

    }
    @GetMapping
    public List<Categoria> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);

        if(categoriaOptional.isPresent()){
            return ResponseEntity.ok(categoriaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Long id){
        service.deleteById(id);
        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
//    @PutMapping("/{id}")
//    public Optional<Categoria>(@PathVariable String id, @RequestBody String entity) {
//        //TODO: process PUT request
//        return entity;
//    }


}

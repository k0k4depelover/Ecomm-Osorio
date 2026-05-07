package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.compras.osorio_ecomm.entities.Categoria;
import com.portal.compras.osorio_ecomm.entities.Producto;
import com.portal.compras.osorio_ecomm.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
     @Autowired
     private CategoriaRepository repository;

    @Override
    public List<Producto> findByCategoriaNombre(String nombre) {
        return repository.findProductoByNombreCat(nombre);
    }

    @Override
    public List<Categoria> findAll() {
       return (List<Categoria>) repository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }


    }

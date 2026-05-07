package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import com.portal.compras.osorio_ecomm.entities.Categoria;
import com.portal.compras.osorio_ecomm.entities.Producto;

public interface CategoriaService {
    List <Producto> findByCategoriaNombre(String nombre);
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);

}

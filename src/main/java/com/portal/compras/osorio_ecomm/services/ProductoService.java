package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import com.portal.compras.osorio_ecomm.entities.Producto;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    Optional<Producto> update(Producto producto, Long id);

}

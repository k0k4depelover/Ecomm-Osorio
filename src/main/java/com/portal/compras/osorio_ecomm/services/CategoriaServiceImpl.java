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

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Categoria> updateCategorua(Long id, Categoria categoria) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isPresent()){
            categoriaOptional.map(categoriaDb ->{
                categoriaDb.setNombre(categoria.getNombre());
                categoriaDb.setDescripcion(categoria.getDescripcion());
                categoriaDb.setOrden(categoria.getOrden());
                categoriaDb.setActive(categoria.getActive());
                categoriaDb.setFecha_creacion(categoria.getFecha_creacion());

                return repository.save(categoriaDb);

            });
        }
        return categoriaOptional;

    }
    }

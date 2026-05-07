package com.portal.compras.osorio_ecomm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.portal.compras.osorio_ecomm.entities.Categoria;
import com.portal.compras.osorio_ecomm.entities.Producto;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
    @Query(value = "SELECT p FROM Categoria c JOIN c.ProductoCat p WHERE c.nombre LIKE %:nombre%")
    List<Producto> findProductoByNombreCat(@Param("nombre") String nombre);



}

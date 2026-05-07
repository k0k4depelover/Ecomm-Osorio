package com.portal.compras.osorio_ecomm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.compras.osorio_ecomm.entities.Producto;
import com.portal.compras.osorio_ecomm.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl  implements ProductoService{

    @Autowired
    private ProductoRepository repository;
    @Override
    public List<Producto> findAll() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return repository.findById(id);

    }

    @SuppressWarnings("null")
    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
       }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Producto> update(Producto producto, Long id) {
        Optional<Producto> productOptional= repository.findById(id);
        if(productOptional.isPresent()){
            productOptional.map(productoDb ->{
                if (producto.getNombre() != null)
                    productoDb.setNombre(producto.getNombre());
                if (producto.getDescripcion_corta() != null)
                    productoDb.setDescripcion_corta(producto.getDescripcion_corta());
                if (producto.getDescripcion_larga() != null)
                    productoDb.setDescripcion_larga(producto.getDescripcion_larga());
                if (producto.getPrecio_venta() != null)
                    productoDb.setPrecio_venta(producto.getPrecio_venta());
                if (producto.getStock() != null)
                    productoDb.setStock(producto.getStock());
                if (producto.getActive() != null)
                    productoDb.setActive(producto.getActive());

                return repository.save(productoDb);
            } );
        }

        return productOptional;


    }

}

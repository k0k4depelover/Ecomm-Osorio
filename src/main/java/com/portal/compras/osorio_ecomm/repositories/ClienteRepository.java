package com.portal.compras.osorio_ecomm.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portal.compras.osorio_ecomm.entities.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("select c from Cliente c WHERE c.active=?1")
    List<Cliente> findAllByActiveYN(String active);

    @Modifying
    @Query(value= "EXEC sp_insert_cliente_encrypt :nombre, :apellido, :email, :password, :telefono, :fecha_nacimiento, :sexo", nativeQuery = true)
    void sp_insert_cliente_encrypt(
        @Param("nombre") String nombre,
        @Param("apellido") String apellido,
        @Param("email") String email,
        @Param("password") String password,
        @Param("telefono") String telefono,
        @Param("fecha_nacimiento") Date fecha_nacimiento,
        @Param("sexo") String sexo);

    @Modifying
    @Query(value = "EXEC sp_update_cliente_encrypt :id_cliente, :nombre, :apellido, :email, :password, :telefono, :fecha_nacimiento, :sexo", nativeQuery = true)
    void sp_update_cliente_encrypt(
        @Param("id_cliente") Long id_cliente,
        @Param("nombre") String nombre,
        @Param("apellido") String apellido,
        @Param("email") String email,
        @Param("password") String password,
        @Param("telefono") String telefono,
        @Param("fecha_nacimiento") Date fecha_nacimiento,
        @Param("sexo") String sexo);

}

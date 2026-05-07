package com.portal.compras.osorio_ecomm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.compras.osorio_ecomm.entities.BankAccount;
import com.portal.compras.osorio_ecomm.entities.BankAccountDTO;

public interface BankRepository extends CrudRepository<BankAccount, Long>{

    @Query("SELECT NEW com.portal.compras.osorio_ecomm.entities.BankAccountDTO(" +
       "bk.id_usuario, bk.nombre_titular, bk.numero_tarjeta, bk.saldo_disponible) " +
       "FROM BankAccount bk")

    List<BankAccountDTO> findAllBankAccounts();

}

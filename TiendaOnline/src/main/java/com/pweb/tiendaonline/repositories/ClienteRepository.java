package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.entities.Cliente;
import com.pweb.tiendaonline.exceptions.ClienteNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Otros
    Cliente findClienteByEmail(String email);

    List<Cliente> findClienteByDireccionContainingIgnoreCase(String direccion);

    List<Cliente> findClienteByNombreStartsWith(String nombre);

}

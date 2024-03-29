package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Otros
    List<Cliente> findClienteByEmail(String email);

    List<Cliente> findClienteByDireccion(String direccion);

    List<Cliente> findClienteByNombreStartsWith(String nombre);

}

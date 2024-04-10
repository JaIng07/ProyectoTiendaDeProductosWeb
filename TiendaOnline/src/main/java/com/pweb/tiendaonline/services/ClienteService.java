package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.exceptions.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {

    // CRUD
    ClienteDto saveCliente(ClienteToSaveDto cliente);

    ClienteDto findClienteById(Long id) throws ClienteNotFoundException;

    List<ClienteDto> findAllClientes();

    ClienteDto updateCliente(Long id, ClienteToSaveDto cliente);

    void deleteCliente(Long id);

    // Otros
    ClienteDto findClienteByEmail(String email) throws ClienteNotFoundException;

    List<ClienteDto> findClienteByDireccionContainingIgnoreCase(String direccion);

    List<ClienteDto> findClienteByNombreStartsWith(String nombre);

}

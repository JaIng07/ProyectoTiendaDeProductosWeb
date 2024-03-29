package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.cliente.ClienteMapper;
import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.entities.Cliente;
import com.pweb.tiendaonline.exceptions.ClienteNotFoundException;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    // Implementación de los métodos de la interfaz ClienteService
    @Override
    public ClienteDto saveCliente(ClienteToSaveDto clienteDto) {
        Cliente cliente = clienteMapper.clienteSaveDtoToClienteEntity(clienteDto);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.clienteEntityToClienteDto(clienteGuardado);
    }

    @Override
    public ClienteDto findClienteById(Long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado"));
        return clienteMapper.clienteEntityToClienteDto(cliente);
    }

    @Override
    public List<ClienteDto> findAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> clienteMapper.clienteEntityToClienteDto(cliente))
                .toList();
    }

    @Override
    public ClienteDto updateCliente(Long id, ClienteToSaveDto cliente) {
        return null;
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteDto findClienteByEmail(String email) throws ClienteNotFoundException {
        return null;
    }

    @Override
    public List<ClienteDto> findClienteByDireccion(String direccion) {
        return null;
    }

    @Override
    public List<ClienteDto> findClienteByNombreStartsWith(String nombre) {
        return null;
    }
}

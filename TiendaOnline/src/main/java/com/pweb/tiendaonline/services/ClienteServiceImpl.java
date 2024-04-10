package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.cliente.ClienteMapper;
import com.pweb.tiendaonline.dtos.cliente.ClienteToSaveDto;
import com.pweb.tiendaonline.entities.Cliente;
import com.pweb.tiendaonline.exceptions.ClienteNotFoundException;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public ClienteDto updateCliente(Long id, ClienteToSaveDto clienteDto) {
        Cliente clienteInDb = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado"));

        clienteInDb.setNombre(clienteDto.nombre());
        clienteInDb.setEmail(clienteDto.email());
        clienteInDb.setDireccion(clienteDto.direccion());

        Cliente clienteGuardado = clienteRepository.save(clienteInDb);

        return clienteMapper.clienteEntityToClienteDto(clienteGuardado);
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteDto findClienteByEmail(String email) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findClienteByEmail(email);
        if (Objects.isNull(cliente))
            throw new ClienteNotFoundException("Cliente no encontrado");
        return clienteMapper.clienteEntityToClienteDto(cliente);
    }

    @Override
    public List<ClienteDto> findClienteByDireccionContainingIgnoreCase(String direccion) {
        List<Cliente> clientes = clienteRepository.findClienteByDireccionContainingIgnoreCase(direccion);

        if(clientes.isEmpty())
            throw new ClienteNotFoundException("Cliente no encontrado");

        List<ClienteDto> clienteDtos = new ArrayList<>();

        clientes.forEach(cliente -> {
            ClienteDto clienteDto = clienteMapper.clienteEntityToClienteDto(cliente);
            clienteDtos.add(clienteDto);
        });

        return clienteDtos;
    }

    @Override
    public List<ClienteDto> findClienteByNombreStartsWith(String nombre) {
        List<Cliente> clientes = clienteRepository.findClienteByNombreStartsWith(nombre);
        if(clientes.isEmpty())
            throw new ClienteNotFoundException("Cliente no encontrado");
        return clientes.stream()
                .map(cliente -> clienteMapper.clienteEntityToClienteDto(cliente))
                .toList();
    }

}

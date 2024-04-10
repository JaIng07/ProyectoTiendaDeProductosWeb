package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoMapper;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Cliente;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.exceptions.PedidoNotFoundException;
import com.pweb.tiendaonline.repositories.ClienteRepository;
import com.pweb.tiendaonline.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoServiceImpl(PedidoMapper pedidoMapper, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoMapper = pedidoMapper;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    // Implementación de los métodos de la interfaz PedidoService


    @Override
    public PedidoDto savePedido(PedidoToSaveDto pedidoDto) {

        Pedido pedidoToSaveEntity = pedidoMapper.pedidoSaveDtoToPedidoEntity(pedidoDto);

        Optional<Cliente> cliente = clienteRepository.findById(pedidoToSaveEntity.getCliente().getId());

        if(cliente.isEmpty())
            throw new PedidoNotFoundException("Cliente no encontrado");

        pedidoToSaveEntity.setCliente(cliente.get());

        System.out.println(pedidoToSaveEntity);

        Pedido pedidoGuardado = pedidoRepository.save(pedidoToSaveEntity);

        System.out.println(pedidoGuardado);;

        return pedidoMapper.pedidoEntityToPedidoDto(pedidoGuardado);
    }

    @Override
    public PedidoDto findPedidoById(Long id) throws PedidoNotFoundException {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido no encontrado"));
        return pedidoMapper.pedidoEntityToPedidoDto(pedido);
    }

    @Override
    public List<PedidoDto> findAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedido -> pedidoMapper.pedidoEntityToPedidoDto(pedido))
                .toList();
    }

    @Override
    public PedidoDto updatePedido(Long id, PedidoToSaveDto pedidoToUpdate) {

        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if(pedidoExistente.isEmpty())
            throw new PedidoNotFoundException("Pedido no encontrado");

        if (pedidoToUpdate.fechaPedido() != null)
            pedidoExistente.get().setFechaPedido(pedidoToUpdate.fechaPedido());

        if (pedidoToUpdate.status() != null)
            pedidoExistente.get().setStatus(pedidoToUpdate.status());

        Pedido pedidoActualizado = pedidoRepository.save(pedidoExistente.get());

        return pedidoMapper.pedidoEntityToPedidoDto(pedidoActualizado);
    }

    @Override
    public void deletePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Pedido no encontrado"));
        pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoDto> findPedidoByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Pedido> pedidos = pedidoRepository.findPedidoByFechaPedidoBetween(fechaInicio, fechaFin);
        if (pedidos.isEmpty()) {
            throw new PedidoNotFoundException("No se encontraron pedidos en el rango de fechas");
        }
        return pedidos.stream()
                .map(pedido -> pedidoMapper.pedidoEntityToPedidoDto(pedido))
                .toList();
    }

    @Override
    public List<PedidoDto> findPedidoByClienteAndStatus(Long idCliente, PedidoStatus status) {
        List<Pedido> pedidos = pedidoRepository.findPedidoByClienteAndStatus(idCliente, status);
        if (pedidos.isEmpty()) {
            throw new PedidoNotFoundException("No se encontraron pedidos con el cliente y estado especificados");
        }
        return pedidos.stream()
                .map(pedido -> pedidoMapper.pedidoEntityToPedidoDto(pedido))
                .toList();
    }

    @Override
    public List<PedidoDto> findPedidoByClienteWithItems(Long idCliente) {
        List<Pedido> pedidos = pedidoRepository.findPedidoByClienteWithItems(idCliente);
        if (pedidos.isEmpty()) {
            throw new PedidoNotFoundException("No se encontraron pedidos con el cliente especificado");
        }
        return pedidos.stream()
                .map(pedido -> pedidoMapper.pedidoEntityToPedidoDto(pedido))
                .toList();
    }

}

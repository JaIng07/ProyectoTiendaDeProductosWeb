package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoMapper;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.exceptions.PedidoNotFoundException;
import com.pweb.tiendaonline.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoMapper pedidoMapper;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoMapper pedidoMapper, PedidoRepository pedidoRepository) {
        this.pedidoMapper = pedidoMapper;
        this.pedidoRepository = pedidoRepository;
    }

    // Implementación de los métodos de la interfaz PedidoService


    @Override
    public PedidoDto savePedido(PedidoToSaveDto pedidoDto) {
        Pedido pedido = pedidoMapper.pedidoSaveDtoToPedidoEntity(pedidoDto);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
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
    public PedidoDto updatePedido(Long id, PedidoToSaveDto pedido) {
        return null;
    }

    @Override
    public void deletePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Pedido no encontrado"));
        pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoDto> findPedidoByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return null;
    }

    @Override
    public List<PedidoDto> findPedidoByClienteAndStatus(Long idCliente, PedidoStatus status) {
        return null;
    }

    @Override
    public List<PedidoDto> findPedidoByClienteWithItems(Long idCliente) {
        return null;
    }
}

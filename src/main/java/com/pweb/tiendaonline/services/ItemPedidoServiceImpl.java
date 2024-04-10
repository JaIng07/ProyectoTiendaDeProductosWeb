package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoMapper;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoToSaveDto;
import com.pweb.tiendaonline.entities.ItemPedido;
import com.pweb.tiendaonline.exceptions.ItemPedidoNotFoundException;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.repositories.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoMapper itemPedidoMapper;
    private final ItemPedidoRepository itemPedidoRepository;


    public ItemPedidoServiceImpl(ItemPedidoMapper itemPedidoMapper, ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoMapper = itemPedidoMapper;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    // Implementación de los métodos de la interfaz ItemPedidoService


    @Override
    public ItemPedidoDto saveItemPedido(ItemPedidoToSaveDto itemPedidoDto) {
        ItemPedido itemPedido = itemPedidoMapper.itemPedidoSaveDtoToItemPedidoEntity(itemPedidoDto);
        ItemPedido itemPedidoGuardado = itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedidoGuardado);
    }

    @Override
    public ItemPedidoDto findItemPedidoById(Long id) throws ItemPedidoNotFoundException {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException("ItemPedido no encontrado"));
        return itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedido);
    }

    @Override
    public List<ItemPedidoDto> findAllItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos.stream()
                .map(itemPedido -> itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedido))
                .toList();
    }

    @Override
    public ItemPedidoDto updateItemPedido(Long id, ItemPedidoToSaveDto itemPedidoDto) {
        ItemPedido itemPedidoInDb = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNotFoundException("ItemPedido no encontrado"));

        itemPedidoInDb.setCantidad(itemPedidoDto.cantidad());
        itemPedidoInDb.setPrecioUnitario(itemPedidoDto.precioUnitario());

        ItemPedido itemPedidoGuardado = itemPedidoRepository.save(itemPedidoInDb);

        return itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedidoGuardado);
    }

    @Override
    public void deleteItemPedido(Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("ItemPedido no encontrado"));
        itemPedidoRepository.delete(itemPedido);
    }

    @Override
    public List<ItemPedidoDto> findItemPedidoByPedidoId(Long idPedido) {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findItemPedidoByPedidoId(idPedido);
        if (itemPedidos.isEmpty())
            throw new ItemPedidoNotFoundException("No se encontraron ItemPedidos para el Pedido con id: " + idPedido);
        return itemPedidos.stream()
                .map(itemPedido -> itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedido))
                .toList();
    }

    @Override
    public List<ItemPedidoDto> findItemPedidoByProductoId(Long idProducto) {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findItemPedidoByProductoId(idProducto);
        if (itemPedidos.isEmpty())
            throw new ItemPedidoNotFoundException("No se encontraron ItemPedidos para el Producto con id: " + idProducto);
        return itemPedidos.stream()
                .map(itemPedido -> itemPedidoMapper.itemPedidoEntityToItemPedidoDto(itemPedido))
                .toList();
    }

    @Override
    public Double findTotalByProductoId(Long idProducto) {
        return itemPedidoRepository.findTotalByProductoId(idProducto);
    }

}

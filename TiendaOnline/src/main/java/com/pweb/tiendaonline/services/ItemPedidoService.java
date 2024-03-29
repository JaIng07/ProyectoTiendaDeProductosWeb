package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoToSaveDto;
import com.pweb.tiendaonline.exceptions.ItemPedidoNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoService {

    // CRUD
    ItemPedidoDto saveItemPedido(ItemPedidoToSaveDto itemPedido);

    ItemPedidoDto findItemPedidoById(Long id) throws ItemPedidoNotFoundException;

    List<ItemPedidoDto> findAllItemPedidos();

    ItemPedidoDto updateItemPedido(Long id, ItemPedidoToSaveDto itemPedido);

    void deleteItemPedido(Long id);

    // Otros
    List<ItemPedidoDto> findItemPedidoByPedidoId(Long idPedido);

    List<ItemPedidoDto> findItemPedidoByProductoId(Long idProducto);

    @Query("SELECT SUM(ip.cantidad * ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id = :idProducto")
    Double findTotalByProductoId(@Param("idProducto") Long idProducto);

}

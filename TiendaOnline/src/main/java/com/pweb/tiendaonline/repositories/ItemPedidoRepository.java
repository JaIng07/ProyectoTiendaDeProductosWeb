package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    // CRUD
    void saveItemPedido(ItemPedido itemPedido);

    ItemPedido findItemPedidoById(Long id);

    List<ItemPedido> findAllItemPedido();

    void updateItemPedido(ItemPedido itemPedido);

    void deleteItemPedidoById(Long id);

    // Otros
    List<ItemPedido> findItemPedidoByPedidoId(Long idPedido);

    List<ItemPedido> findItemPedidoByProductoId(Long idProducto);

    @Query("SELECT SUM(ip.cantidad * ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id = :idProducto")
    Double findTotalByProductoId(@Param("idProducto") Long idProducto);

}

package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoToSaveDto;
import com.pweb.tiendaonline.entities.ItemPedido;
import com.pweb.tiendaonline.exceptions.ItemPedidoNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    // Otros
    List<ItemPedido> findItemPedidoByPedidoId(Long idPedido);

    List<ItemPedido> findItemPedidoByProductoId(Long idProducto);

    @Query("SELECT SUM(ip.cantidad * ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id = :idProducto")
    Double findTotalByProductoId(@Param("idProducto") Long idProducto);

}

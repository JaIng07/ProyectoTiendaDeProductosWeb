package com.pweb.tiendaonline.repositories;

import java.util.List;

import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.pweb.tiendaonline.exceptions.DetalleEnvioNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pweb.tiendaonline.entities.DetalleEnvio;
import com.pweb.tiendaonline.entities.PedidoStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {

    // Otros
    List<DetalleEnvio> findDetalleEnvioByPedidoId(Long idPedido);

    List<DetalleEnvio> findDetalleEnvioByTransportadora(String transportadora);

    // Buscar los detalles de envio por estado
    // Para hacer esto hay que hacerlo manual con @Query pero tengo cule flojera
    // La forma seria metiendose con Pedido hasta llegar a PedidoStatus
    // Siguiendo la siguiente ruta: DetalleEnvio -> Pedido -> PedidoStatus
    // Nelson ponte la 10 ahí, todo bien.

    //FROM Nelson: Sova.
    @Query("SELECT ped.detalleEnvio FROM Pedido ped WHERE ped.status = : pedidoStatus")
    List<DetalleEnvio> findDetalleEnvioByEstado(@Param("pedidoStatus") PedidoStatus pedidoStatus);
}

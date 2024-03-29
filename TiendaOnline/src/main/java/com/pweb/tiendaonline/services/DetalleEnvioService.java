package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioDto;
import com.pweb.tiendaonline.dtos.detalleEnvio.DetalleEnvioToSaveDto;
import com.pweb.tiendaonline.entities.DetalleEnvio;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.DetalleEnvioNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetalleEnvioService {

    // CRUD
    DetalleEnvioDto saveDetalleEnvio(DetalleEnvioToSaveDto detalleEnvio);

    DetalleEnvioDto findDetalleEnvioById(Long id) throws DetalleEnvioNotFoundException;

    List<DetalleEnvioDto> findAllDetallesEnvio();

    DetalleEnvioDto updateDetalleEnvio(Long id, DetalleEnvioToSaveDto detalleEnvio);

    void deleteDetalleEnvio(Long id);

    // Otros
    List<DetalleEnvioDto> findDetalleEnvioByPedidoId(Long idPedido);

    List<DetalleEnvioDto> findDetalleEnvioByTransportadora(String transportadora);

    @Query("SELECT ped.detalleEnvio FROM Pedido ped WHERE ped.status = : pedidoStatus")
    List<DetalleEnvioDto> findDetalleEnvioByEstado(@Param("pedidoStatus") PedidoStatus pedidoStatus);

}

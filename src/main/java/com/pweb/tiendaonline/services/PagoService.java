package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.pago.PagoDto;
import com.pweb.tiendaonline.dtos.pago.PagoToSaveDto;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.exceptions.PagoNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagoService {

    // CRUD
    PagoDto savePago(PagoToSaveDto pago);

    PagoDto findPagoById(Long id) throws PagoNotFoundException;

    List<PagoDto> findAllPagos();

    PagoDto updatePago(Long id, PagoToSaveDto pago);

    void deletePago(Long id);

    // Otros
    List<PagoDto> findPagoByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<PagoDto> findPagoByPedidoIdAndMetodoPago(Long idPedido, PagoMetodo metodoPago);

}

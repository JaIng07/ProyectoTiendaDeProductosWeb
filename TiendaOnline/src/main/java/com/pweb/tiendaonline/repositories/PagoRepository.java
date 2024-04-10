package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.dtos.pago.PagoToSaveDto;
import com.pweb.tiendaonline.entities.Pago;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.exceptions.PagoNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Otros
    List<Pago> findPagoByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Pago> findPagoByPedidoIdAndMetodoPago(Long idPedido, PagoMetodo metodoPago);

}

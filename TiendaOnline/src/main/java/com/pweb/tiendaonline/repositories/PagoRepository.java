package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.Pago;
import com.pweb.tiendaonline.entities.PagoMetodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Otros
    List<Pago> findPagoByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Pago> findPagoByPedidoIdAndMetodoPago(Long idPedido, PagoMetodo metodoPago);

}

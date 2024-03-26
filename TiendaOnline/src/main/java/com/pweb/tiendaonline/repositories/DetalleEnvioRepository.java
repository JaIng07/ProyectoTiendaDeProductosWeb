package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.DetalleEnvio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {

    // CRUD
    void saveDetalleEnvio(DetalleEnvio detalleEnvio);

    DetalleEnvio findDetalleEnvioById(Long id);

    List<DetalleEnvio> findAllDetalleEnvio();

    void updateDetalleEnvio(DetalleEnvio detalleEnvio);

    void deleteDetalleEnvioById(Long id);

    // Otros
    List<DetalleEnvio> findDetalleEnvioByPedidoId(Long idPedido);

    List<DetalleEnvio> findDetalleEnvioByTransportadora(String transportadora);

    // Buscar los detalles de envio por estado
    // Para hacer esto hay que hacerlo manual con @Query pero tengo cule flojera
    // La forma seria metiendose con Pedido hasta llegar a PedidoStatus
    // Siguiendo la siguiente ruta: DetalleEnvio -> Pedido -> PedidoStatus
    // Nelson ponte la 10 ahí, todo bien.

}

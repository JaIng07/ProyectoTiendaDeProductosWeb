package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Pagos")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "totalPago")
    private Double totalPago;

    @Column(name = "fechaPago")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPago;

    @Column(name = "metodoPago")
    @Enumerated(EnumType.STRING)
    private PagoMetodo metodoPago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedido", referencedColumnName = "id")
    private Pedido pedido;

   /* @PrePersist
    public void validarFechaPago() {
        LocalDateTime fechaPedido = pedido.getFechaPedido(); // Obtener la fecha de realización del pedido
        if (fechaPago.isBefore(fechaPedido)) {
            throw new IllegalStateException("La fecha y hora de pago no puede ser anterior a la fecha y hora de realización del pedido");
        }
    }*/

}

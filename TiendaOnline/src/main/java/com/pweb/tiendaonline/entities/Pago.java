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

    //NO OLVIDAR colocar una restriccion que no permita que la fecha y hora de pago sea anterior a la fecha y hora de la realizacion del pedido
    @Column(name = "fechaPago")
    private LocalDateTime fechaPago;

    @Column(name = "metodoPago")
    private PagoMetodo metodoPago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedido", referencedColumnName = "id")
    private Pedido pedido;

}

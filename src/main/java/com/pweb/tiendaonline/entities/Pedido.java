package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaPedido")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPedido;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemsPedidos;

    @OneToOne(mappedBy = "pedido")
    private Pago pago;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;

}

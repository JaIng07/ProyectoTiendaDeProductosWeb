package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ItemPedidos")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Cantidad")
    private Integer cantidad;

    @Column(name = "PrecioUnitario")
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "idPedido", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Producto producto;

}

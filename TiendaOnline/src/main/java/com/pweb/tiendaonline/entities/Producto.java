package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Productos")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Stock")
    private Integer stock;

    @OneToMany(mappedBy = "producto")
    private List<ItemPedido> itemPedidos;

}

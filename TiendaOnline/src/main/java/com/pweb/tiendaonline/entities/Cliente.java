package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Clientes")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Email")
    private String email;

    @Column(name = "Direccion")
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

}

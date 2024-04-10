package com.pweb.tiendaonline.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "detalleEnvios")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetalleEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Transportadora")
    private String transportadora;

    @Column(name = "numeroGuia")
    private String numeroGuia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPedido", referencedColumnName = "id")
    private Pedido pedido;

}

package com.ariel.backend.aplication_cumpita_sac.entities;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boletas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroBoleta;
    private double subTotal;
    private double igv;
    private double total;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("boleta")
    private List<DetalleBoleta> detalles = new ArrayList<>();

}

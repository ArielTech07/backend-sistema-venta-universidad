package com.ariel.backend.aplication_cumpita_sac.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "cliente_id",nullable = false)  
    private Clientes cliente;
    
    
}

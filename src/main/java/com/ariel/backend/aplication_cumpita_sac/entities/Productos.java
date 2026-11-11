package com.ariel.backend.aplication_cumpita_sac.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "productos")
@Entity
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor    
public class Productos {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String nombre;
    private String tipo;
    private double precio;
    private int cantidad;
}

package com.ariel.backend.aplication_cumpita_sac.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "clientes") 
@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor  
public class Clientes {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore  
    private List<Boletas> boletas=new ArrayList<>();
}

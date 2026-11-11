package com.ariel.backend.aplication_cumpita_sac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.backend.aplication_cumpita_sac.entities.Productos;

public interface ProductoRepository extends JpaRepository<Productos,Long> {

}

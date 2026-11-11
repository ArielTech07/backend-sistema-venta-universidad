package com.ariel.backend.aplication_cumpita_sac.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;

public interface BoletaRepository extends JpaRepository<Boletas,Long>{
        List<Boletas> findByClienteDni(String dni);
}

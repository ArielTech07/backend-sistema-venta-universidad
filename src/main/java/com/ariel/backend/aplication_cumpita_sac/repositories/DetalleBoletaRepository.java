package com.ariel.backend.aplication_cumpita_sac.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.backend.aplication_cumpita_sac.entities.DetalleBoleta;

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta,Long> {
        List<DetalleBoleta> findByBoletaId(Long boletaId);
}

package com.ariel.backend.aplication_cumpita_sac.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes,Long> {
    Optional<Clientes> findByDni(String dni);
}

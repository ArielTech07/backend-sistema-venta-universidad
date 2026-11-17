package com.ariel.backend.aplication_cumpita_sac.services;

import java.util.List;
import java.util.Optional;

import com.ariel.backend.aplication_cumpita_sac.dto.BoletaRequest;
import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;

public interface BoletaService {
    List<Boletas> listarTodas();
    Optional<Boletas> buscarPorId(Long id);
    Boletas registrarBoleta(BoletaRequest request);
    List<Boletas> obtenerBoletasPorCliente(String dni);
}

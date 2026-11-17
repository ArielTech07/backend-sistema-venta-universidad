package com.ariel.backend.aplication_cumpita_sac.services;

import java.util.List;
import java.util.Optional;

import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;

public interface ClienteService {
    List<Clientes> listarTodos();
    Optional<Clientes> buscarPorId(Long id);
    Optional<Clientes> buscarPorDni(String dni);
    Clientes registrarCliente(Clientes cliente);
}

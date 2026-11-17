package com.ariel.backend.aplication_cumpita_sac.services;

import java.util.List;
import java.util.Optional;

import com.ariel.backend.aplication_cumpita_sac.entities.Productos;

public interface ProductoService {
    List<Productos> listarTodos();
    Optional<Productos> buscarPorId(Long id);
    Productos guardar(Productos producto);
    Optional<Productos> actualizar(Long id, Productos producto);
    boolean eliminar(Long id);
}

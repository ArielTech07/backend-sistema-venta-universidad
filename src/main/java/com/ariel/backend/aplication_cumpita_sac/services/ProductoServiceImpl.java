package com.ariel.backend.aplication_cumpita_sac.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.backend.aplication_cumpita_sac.entities.Productos;
import com.ariel.backend.aplication_cumpita_sac.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Productos guardar(Productos producto) {
        if (producto.getId() != null) {
            return productoRepository.findById(producto.getId())
                    .orElseGet(() -> productoRepository.save(producto));
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Productos> actualizar(Long id, Productos producto) {
        return productoRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(producto.getNombre());
                    existing.setTipo(producto.getTipo());
                    existing.setPrecio(producto.getPrecio());
                    existing.setCantidad(producto.getCantidad());
                    return productoRepository.save(existing);
                });
    }

    @Override
    @Transactional
    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

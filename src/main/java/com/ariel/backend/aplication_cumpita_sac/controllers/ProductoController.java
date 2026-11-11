package com.ariel.backend.aplication_cumpita_sac.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.backend.aplication_cumpita_sac.entities.Productos;
import com.ariel.backend.aplication_cumpita_sac.repositories.ProductoRepository;

@RestController
@RequestMapping("/api/productos") 
public class ProductoController {

    final ProductoRepository productoRepository;

    ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Productos> viewAll(){
        return productoRepository.findAll();
    }
    
    @PostMapping
    public Productos addProduct(@RequestBody Productos producto){
        if (producto.getId() != null) {
            return productoRepository.findById(producto.getId())
                .orElseGet(() -> productoRepository.save(producto));
        }
        return productoRepository.save(producto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long id, @RequestBody Productos producto) {
    return productoRepository.findById(id)
            .map(existing -> {
                existing.setNombre(producto.getNombre());
                existing.setTipo(producto.getTipo());
                existing.setPrecio(producto.getPrecio());
                return ResponseEntity.ok(productoRepository.save(existing));
            })
            .orElse(ResponseEntity.notFound().build());
}

}

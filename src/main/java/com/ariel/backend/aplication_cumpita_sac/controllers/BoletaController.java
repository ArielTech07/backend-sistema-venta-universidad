package com.ariel.backend.aplication_cumpita_sac.controllers;

import java.time.LocalDate;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;
import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.repositories.BoletaRepository;
import com.ariel.backend.aplication_cumpita_sac.repositories.ClienteRepository;

@RestController 
@RequestMapping("/api/boletas") 
public class BoletaController {
    private final BoletaRepository boletaRepository;

    private final ClienteRepository clienteRepository;

    BoletaController(BoletaRepository boletaRepository, ClienteRepository clienteRepository) {
        this.boletaRepository = boletaRepository;
        this.clienteRepository = clienteRepository;
    }
    

    

    public static class BoletaRequest {
        public String numeroBoleta;
        public String dniCliente;
        public double subtotal;
        public double igv;
        public double total;
    }

    @PostMapping
    public ResponseEntity<?> registrarBoleta(@RequestBody BoletaRequest request) {
        // 1. Buscamos al cliente por su DNI
        Optional<Clientes> clienteOpt = clienteRepository.findByDni(request.dniCliente);
        
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El cliente no está registrado.");
        }
        // 2. Creamos y armamos la boleta
        Boletas boleta = new Boletas();
        boleta.setNumeroBoleta(request.numeroBoleta);
        boleta.setSubTotal(request.subtotal);
        boleta.setIgv(request.igv);
        boleta.setTotal(request.total);
        boleta.setFecha(LocalDate.now());
        boleta.setCliente(clienteOpt.get()); 
        Boletas guardada = boletaRepository.save(boleta);
        return ResponseEntity.ok(guardada);
    }

    @GetMapping("/cliente/{dni}")
    public List<Boletas> obtenerBoletasPorCliente(@PathVariable String dni) {
        return boletaRepository.findByClienteDni(dni);
    }
    
}

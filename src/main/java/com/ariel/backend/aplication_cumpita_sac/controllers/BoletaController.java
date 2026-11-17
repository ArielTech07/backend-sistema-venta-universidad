package com.ariel.backend.aplication_cumpita_sac.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.backend.aplication_cumpita_sac.dto.BoletaRequest;
import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;
import com.ariel.backend.aplication_cumpita_sac.services.BoletaService;

@RestController 
@RequestMapping("/api/boletas") 
public class BoletaController {

    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping
    public List<Boletas> listarBoletas() {
        return boletaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<?> registrarBoleta(@RequestBody BoletaRequest request) {
        try {
            Boletas guardada = boletaService.registrarBoleta(request);
            return ResponseEntity.ok(guardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{dni}")
    public List<Boletas> obtenerBoletasPorCliente(@PathVariable String dni) {
        return boletaService.obtenerBoletasPorCliente(dni);
    }
}

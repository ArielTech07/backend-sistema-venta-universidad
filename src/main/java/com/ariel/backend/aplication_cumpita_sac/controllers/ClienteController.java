package com.ariel.backend.aplication_cumpita_sac.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")  
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Clientes> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Clientes> obtenerPorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Clientes registrarCliente(@RequestBody Clientes cliente) {
        return clienteService.registrarCliente(cliente);
    }   
}

package com.ariel.backend.aplication_cumpita_sac.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")  
public class ClienteController {
    private final ClienteRepository clienteRepository;

    ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public Clientes registrarCliente(@RequestBody Clientes cliente){
        return clienteRepository.findByDni(cliente.getDni())
        .orElseGet(()->clienteRepository.save(cliente));
    }   
}

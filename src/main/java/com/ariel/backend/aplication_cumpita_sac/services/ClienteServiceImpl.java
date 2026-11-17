package com.ariel.backend.aplication_cumpita_sac.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Clientes> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clientes> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Clientes> buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    @Transactional
    public Clientes registrarCliente(Clientes cliente) {
        return clienteRepository.findByDni(cliente.getDni())
                .orElseGet(() -> clienteRepository.save(cliente));
    }
}

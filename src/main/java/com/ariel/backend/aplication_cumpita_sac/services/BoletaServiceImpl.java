package com.ariel.backend.aplication_cumpita_sac.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.backend.aplication_cumpita_sac.dto.BoletaRequest;
import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;
import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.repositories.BoletaRepository;
import com.ariel.backend.aplication_cumpita_sac.repositories.ClienteRepository;

@Service
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;
    private final ClienteRepository clienteRepository;

    public BoletaServiceImpl(BoletaRepository boletaRepository, ClienteRepository clienteRepository) {
        this.boletaRepository = boletaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boletas> listarTodas() {
        return boletaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Boletas> buscarPorId(Long id) {
        return boletaRepository.findById(id);
    }

    @Override
    @Transactional
    public Boletas registrarBoleta(BoletaRequest request) {
        Clientes cliente = clienteRepository.findByDni(request.getDniCliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente no está registrado."));

        Boletas boleta = new Boletas();
        boleta.setNumeroBoleta(request.getNumeroBoleta());
        boleta.setSubTotal(request.getSubtotal());
        boleta.setIgv(request.getIgv());
        boleta.setTotal(request.getTotal());
        boleta.setFecha(LocalDate.now());
        boleta.setCliente(cliente);

        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boletas> obtenerBoletasPorCliente(String dni) {
        return boletaRepository.findByClienteDni(dni);
    }
}

package com.ariel.backend.aplication_cumpita_sac.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ariel.backend.aplication_cumpita_sac.dto.BoletaRequest;
import com.ariel.backend.aplication_cumpita_sac.dto.DetalleRequest;
import com.ariel.backend.aplication_cumpita_sac.entities.Boletas;
import com.ariel.backend.aplication_cumpita_sac.entities.Clientes;
import com.ariel.backend.aplication_cumpita_sac.entities.DetalleBoleta;
import com.ariel.backend.aplication_cumpita_sac.entities.Productos;
import com.ariel.backend.aplication_cumpita_sac.repositories.BoletaRepository;
import com.ariel.backend.aplication_cumpita_sac.repositories.ClienteRepository;
import com.ariel.backend.aplication_cumpita_sac.repositories.ProductoRepository;

@Service
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public BoletaServiceImpl(BoletaRepository boletaRepository, ClienteRepository clienteRepository,
            ProductoRepository productoRepository) {
        this.boletaRepository = boletaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
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
        boleta.setFecha(LocalDate.now());
        boleta.setCliente(cliente);

        double subtotalBoleta = 0;

        if (request.getDetalles() != null) {
            for (DetalleRequest item : request.getDetalles()) {
                Productos producto = productoRepository.findById(item.getProductoId())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Producto con ID: " + item.getProductoId() + " no encontrado"));

                // Creamos el detalle con la cantidad comprada y el precio del producto
                DetalleBoleta detalle = new DetalleBoleta();
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(producto.getPrecio());
                detalle.setSubtotal(item.getCantidad() * producto.getPrecio());
                detalle.setBoleta(boleta);
                detalle.setProducto(producto);
                boleta.getDetalles().add(detalle);
                subtotalBoleta += detalle.getSubtotal();
            }
        }
            double igv = subtotalBoleta * 0.18;
            boleta.setSubTotal(subtotalBoleta);
            boleta.setIgv(igv);
            boleta.setTotal(subtotalBoleta + igv);

        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boletas> obtenerBoletasPorCliente(String dni) {
        return boletaRepository.findByClienteDni(dni);
    }
}

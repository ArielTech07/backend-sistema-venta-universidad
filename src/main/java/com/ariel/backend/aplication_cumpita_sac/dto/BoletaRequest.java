package com.ariel.backend.aplication_cumpita_sac.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletaRequest {
    private String numeroBoleta;
    private String dniCliente;
    private List<DetalleRequest> detalles;
}

package com.ariel.backend.aplication_cumpita_sac.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoletaRequest {
    public String numeroBoleta;
    public String dniCliente;
    public double subtotal;
    public double igv;
    public double total;
}

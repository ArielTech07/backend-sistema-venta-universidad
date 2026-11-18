package com.ariel.backend.aplication_cumpita_sac.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRequest {
    private Long productoId;
    private int cantidad;
}

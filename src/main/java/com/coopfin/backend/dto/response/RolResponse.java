package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class RolResponse {

    private Long idRol;
    private String nombre;
    private String descripcion;
}

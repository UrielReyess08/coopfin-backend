package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CooperativaResponse {

    private Long idCooperativa;
    private String nombre;
    private String ruc;
    private String direccion;
    private String telefono;
    private String email;
    private String logoUrl;
    private String colorPrincipal;
    private String colorSecundario;
    private Boolean estado;
}

package com.coopfin.backend.dto.response;

import lombok.Data;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
public class SocioResponse {

    private Long idSocio;
    private String codigoSocio;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private LocalDate fechaIngreso;
    private Boolean estado;
    private Long idUsuario;
    private String username;
    private Long idCooperativa;
    private String nombreCooperativa;
}

package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioResponse {

    private Long idUsuario;
    private String username;
    private String email;
    private Boolean estado;
    private LocalDateTime ultimoLogin;
    private LocalDateTime fechaCreacion;
    private Long idRol;
    private String nombreRol;
    private Long idCooperativa;
    private String nombreCooperativa;
}

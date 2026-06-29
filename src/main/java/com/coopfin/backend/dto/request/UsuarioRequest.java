package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 80, message = "El username debe tener entre 4 y 80 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener como mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres")
    private String email;

    @NotNull(message = "El rol es obligatorio")
    private Long idRol;

    @NotNull(message = "La cooperativa es obligatoria")
    private Long idCooperativa;
}

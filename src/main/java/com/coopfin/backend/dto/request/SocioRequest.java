package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SocioRequest {

    @NotBlank(message = "El código de socio es obligatorio")
    @Size(max = 30, message = "El código de socio no puede superar los 30 caracteres")
    private String codigoSocio;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe contener exactamente 8 dígitos")
    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
    private String apellidos;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccion;

    private Long idUsuario;

    @NotNull(message = "La cooperativa es obligatoria")
    private Long idCooperativa;
}

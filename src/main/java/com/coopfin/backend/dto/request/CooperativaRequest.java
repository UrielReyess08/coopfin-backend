package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class CooperativaRequest {

    @NotBlank(message = "el nombre es obligatorio")
    @Size(
            max = 150,
            message = "El nombre no puede superar los 150 caracteres"
    )
    private String nombre;

    @NotBlank(message = "el RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "el RUC debe contener exactamente 11 dígitos")
    private String ruc;

    @Size(
            max = 255,
            message = "La dirección no puede superar los 255 caracteres"
    )
    private String direccion;

    @NotBlank(message = "el teléfono es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "el correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Size(
            max = 120,
            message = "El correo no puede superar los 120 caracteres"
    )
    private String email;

    @Size(
            max = 500,
            message = "La URL del logo es demasiado larga"
    )
    private String logoUrl;

    @Pattern(
            regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Color hexadecimal inválido"
    )
    private String colorPrincipal;

    @Pattern(
            regexp = "^#([A-Fa-f0-9]{6})$",
            message = "Color hexadecimal inválido"
    )
    private String colorSecundario;
}

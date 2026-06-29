package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class CooperativaRequest {

    @NotBlank
    @Size(max = 150)
    private String nombre;

    @NotBlank
    @Size(min = 11, max = 11)
    private String ruc;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 120)
    private String email;

    private String logoUrl;

    @Size(max = 20)
    private String colorPrincipal;

    @Size(max = 20)
    private String colorSecundario;
}

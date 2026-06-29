package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolRequest {

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Size(max = 150)
    private String descripcion;
}

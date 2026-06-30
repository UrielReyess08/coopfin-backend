package com.coopfin.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoCuotaRequest {

    @NotNull(message = "La cuota es obligatoria")
    private Long idCuota;

    @NotNull(message = "El capital pagado es obligatorio")
    @DecimalMin(value = "0.00", message = "El capital pagado no puede ser negativo")
    private BigDecimal capitalPagado;

    @NotNull(message = "El interés pagado es obligatorio")
    @DecimalMin(value = "0.00", message = "El interés pagado no puede ser negativo")
    private BigDecimal interesPagado;

    @NotNull(message = "La mora pagada es obligatoria")
    @DecimalMin(value = "0.00", message = "La mora pagada no puede ser negativa")
    private BigDecimal moraPagada;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede superar los 50 caracteres")
    private String metodoPago;

    @Size(max = 255, message = "La observación no puede superar los 255 caracteres")
    private String observacion;
}
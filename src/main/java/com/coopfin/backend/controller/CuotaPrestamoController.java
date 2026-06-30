package com.coopfin.backend.controller;

import com.coopfin.backend.dto.response.CuotaPrestamoResponse;
import com.coopfin.backend.service.CuotaPrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuotas-prestamo")
@RequiredArgsConstructor
public class CuotaPrestamoController {

    private final CuotaPrestamoService cuotaPrestamoService;

    @GetMapping("/prestamo/{idPrestamo}")
    public List<CuotaPrestamoResponse> listarPorPrestamo(
            @PathVariable Long idPrestamo
    ) {
        return cuotaPrestamoService.listarPorPrestamo(idPrestamo);
    }
}
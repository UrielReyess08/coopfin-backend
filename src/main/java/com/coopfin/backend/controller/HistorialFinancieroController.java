package com.coopfin.backend.controller;

import com.coopfin.backend.dto.response.HistorialFinancieroResponse;
import com.coopfin.backend.service.HistorialFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historial-financiero")
@RequiredArgsConstructor
public class HistorialFinancieroController {

    private final HistorialFinancieroService historialFinancieroService;

    @GetMapping("/socio/{idSocio}")
    public HistorialFinancieroResponse obtenerPorSocio(@PathVariable Long idSocio) {
        return historialFinancieroService.obtenerPorSocio(idSocio);
    }
}
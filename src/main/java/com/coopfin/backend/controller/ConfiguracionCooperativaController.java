package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.ConfiguracionCooperativaRequest;
import com.coopfin.backend.dto.response.ConfiguracionCooperativaResponse;
import com.coopfin.backend.service.ConfiguracionCooperativaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuraciones-cooperativa")
@RequiredArgsConstructor
public class ConfiguracionCooperativaController {

    private final ConfiguracionCooperativaService configuracionService;

    @PostMapping
    public ConfiguracionCooperativaResponse crear(@Valid @RequestBody ConfiguracionCooperativaRequest request) {
        return configuracionService.crear(request);
    }

    @GetMapping
    public List<ConfiguracionCooperativaResponse> listar() {
        return configuracionService.listar();
    }

    @GetMapping("/{id}")
    public ConfiguracionCooperativaResponse obtenerPorId(@PathVariable Long id) {
        return configuracionService.obtenerPorId(id);
    }

    @GetMapping("/cooperativa/{idCooperativa}")
    public ConfiguracionCooperativaResponse obtenerPorCooperativa(@PathVariable Long idCooperativa) {
        return configuracionService.obtenerPorCooperativa(idCooperativa);
    }

    @PutMapping("/{id}")
    public ConfiguracionCooperativaResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConfiguracionCooperativaRequest request
    ) {
        return configuracionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        configuracionService.eliminar(id);
    }
}

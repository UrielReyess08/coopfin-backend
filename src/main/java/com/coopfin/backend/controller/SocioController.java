package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.SocioRequest;
import com.coopfin.backend.dto.response.SocioResponse;
import com.coopfin.backend.service.SocioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @PostMapping
    public SocioResponse crear(@Valid @RequestBody SocioRequest request) {
        return socioService.crear(request);
    }

    @GetMapping
    public List<SocioResponse> listar() {
        return socioService.listar();
    }

    @GetMapping("/{id}")
    public SocioResponse obtenerPorId(@PathVariable Long id) {
        return socioService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public SocioResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SocioRequest request
    ) {
        return socioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
    }
}

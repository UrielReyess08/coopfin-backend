package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.CooperativaRequest;
import com.coopfin.backend.dto.response.CooperativaResponse;
import com.coopfin.backend.service.CooperativaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cooperativas")
@RequiredArgsConstructor
public class CooperativaController {

    private final CooperativaService cooperativaService;

    @PostMapping
    public CooperativaResponse crear(@Valid @RequestBody CooperativaRequest request) {
        return cooperativaService.crear(request);
    }

    @GetMapping
    public List<CooperativaResponse> listar() {
        return cooperativaService.listar();
    }

    @GetMapping("/{id}")
    public CooperativaResponse obtenerPorId(@PathVariable Long id) {
        return cooperativaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public CooperativaResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CooperativaRequest request
    ) {
        return cooperativaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cooperativaService.eliminar(id);
    }
}

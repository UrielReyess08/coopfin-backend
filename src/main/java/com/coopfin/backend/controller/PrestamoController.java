package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.PrestamoSolicitudRequest;
import com.coopfin.backend.dto.response.PrestamoResponse;
import com.coopfin.backend.model.enums.EstadoPrestamo;
import com.coopfin.backend.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping("/solicitar")
    public PrestamoResponse solicitar(@Valid @RequestBody PrestamoSolicitudRequest request) {
        return prestamoService.solicitar(request);
    }

    @PutMapping("/{id}/aprobar")
    public PrestamoResponse aprobar(@PathVariable Long id) {
        return prestamoService.aprobar(id);
    }

    @GetMapping
    public List<PrestamoResponse> listar() {
        return prestamoService.listar();
    }

    @GetMapping("/{id}")
    public PrestamoResponse obtenerPorId(@PathVariable Long id) {
        return prestamoService.obtenerPorId(id);
    }

    @GetMapping("/socio/{idSocio}")
    public List<PrestamoResponse> listarPorSocio(@PathVariable Long idSocio) {
        return prestamoService.listarPorSocio(idSocio);
    }

    @GetMapping("/estado/{estado}")
    public List<PrestamoResponse> listarPorEstado(@PathVariable EstadoPrestamo estado) {
        return prestamoService.listarPorEstado(estado);
    }
}

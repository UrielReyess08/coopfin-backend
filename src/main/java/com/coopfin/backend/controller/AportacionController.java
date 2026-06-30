package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.AportacionRequest;
import com.coopfin.backend.dto.response.AportacionResponse;
import com.coopfin.backend.service.AportacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aportaciones")
@RequiredArgsConstructor
public class AportacionController {

    private final AportacionService aportacionService;

    @PostMapping
    public AportacionResponse registrar(@Valid @RequestBody AportacionRequest request) {
        return aportacionService.registrar(request);
    }

    @GetMapping
    public List<AportacionResponse> listar() {
        return aportacionService.listar();
    }

    @GetMapping("/socio/{idSocio}")
    public List<AportacionResponse> listarPorSocio(@PathVariable Long idSocio) {
        return aportacionService.listarPorSocio(idSocio);
    }
}
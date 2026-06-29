package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.RolRequest;
import com.coopfin.backend.dto.response.RolResponse;
import com.coopfin.backend.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    public RolResponse create(@Valid @RequestBody RolRequest request) {
        return rolService.crearRol(request);
    }

    @GetMapping
    public List<RolResponse> listarRoles() {
        return rolService.listarRoles();
    }

    @GetMapping("/{id}")
    public RolResponse obtenerRolPorId(@PathVariable Long id) {
        return rolService.obtenerRolPorId(id);
    }

    @PutMapping("/{id}")
    public RolResponse actualizarRol(
            @PathVariable Long id,
            @Valid @RequestBody RolRequest request
    ) {
        return rolService.actualizarRol(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
    }
}

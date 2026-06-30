package com.coopfin.backend.controller;

import com.coopfin.backend.dto.request.PagoCuotaRequest;
import com.coopfin.backend.dto.response.PagoCuotaResponse;
import com.coopfin.backend.service.PagoCuotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos-cuota")
@RequiredArgsConstructor
public class PagoCuotaController {

    private final PagoCuotaService pagoCuotaService;

    @PostMapping
    public PagoCuotaResponse registrarPago(@Valid @RequestBody PagoCuotaRequest request) {
        return pagoCuotaService.registrarPago(request);
    }

    @GetMapping
    public List<PagoCuotaResponse> listar() {
        return pagoCuotaService.listar();
    }

    @GetMapping("/cuota/{idCuota}")
    public List<PagoCuotaResponse> listarPorCuota(@PathVariable Long idCuota) {
        return pagoCuotaService.listarPorCuota(idCuota);
    }
}
package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.response.CuotaPrestamoResponse;
import com.coopfin.backend.repository.CuotaPrestamoRepository;
import com.coopfin.backend.service.CuotaPrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuotaPrestamoServiceImpl implements CuotaPrestamoService {

    private final CuotaPrestamoRepository cuotaPrestamoRepository;

    @Override
    public List<CuotaPrestamoResponse> listarPorPrestamo(Long idPrestamo) {

        return cuotaPrestamoRepository
                .findByPrestamoIdPrestamo(idPrestamo)
                .stream()
                .map(cuota -> CuotaPrestamoResponse.builder()
                        .idCuota(cuota.getIdCuota())
                        .numeroCuota(cuota.getNumeroCuota())
                        .capitalProgramado(cuota.getCapitalProgramado())
                        .interesProgramado(cuota.getInteresProgramado())
                        .montoTotal(cuota.getMontoTotal())
                        .fechaVencimiento(cuota.getFechaVencimiento())
                        .estado(cuota.getEstado())
                        .build())
                .toList();
    }
}
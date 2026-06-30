package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.response.AportacionResponse;
import com.coopfin.backend.dto.response.HistorialFinancieroResponse;
import com.coopfin.backend.dto.response.PrestamoResponse;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.Socio;
import com.coopfin.backend.repository.SocioRepository;
import com.coopfin.backend.service.AportacionService;
import com.coopfin.backend.service.HistorialFinancieroService;
import com.coopfin.backend.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialFinancieroServiceImpl implements HistorialFinancieroService {

    private final SocioRepository socioRepository;
    private final AportacionService aportacionService;
    private final PrestamoService prestamoService;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR','SOCIO')")
    @Override
    public HistorialFinancieroResponse obtenerPorSocio(Long idSocio) {
        Socio socio = socioRepository.findById(idSocio)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + idSocio));

        List<AportacionResponse> aportaciones = aportacionService.listarPorSocio(idSocio);
        List<PrestamoResponse> prestamos = prestamoService.listarPorSocio(idSocio);

        BigDecimal totalAportado = aportaciones.stream()
                .map(AportacionResponse::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPrestado = prestamos.stream()
                .map(PrestamoResponse::getMontoSolicitado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoPendiente = prestamos.stream()
                .map(PrestamoResponse::getSaldoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return HistorialFinancieroResponse.builder()
                .idSocio(socio.getIdSocio())
                .codigoSocio(socio.getCodigoSocio())
                .nombreSocio(socio.getNombres() + " " + socio.getApellidos())
                .totalAportado(totalAportado)
                .totalPrestado(totalPrestado)
                .saldoPendiente(saldoPendiente)
                .aportaciones(aportaciones)
                .prestamos(prestamos)
                .build();
    }
}
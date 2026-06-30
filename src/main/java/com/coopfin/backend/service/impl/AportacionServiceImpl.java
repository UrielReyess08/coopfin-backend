package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.AportacionRequest;
import com.coopfin.backend.dto.response.AportacionResponse;
import com.coopfin.backend.exception.BadRequestException;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.Aportacion;
import com.coopfin.backend.model.entity.ConfiguracionCooperativa;
import com.coopfin.backend.model.entity.Socio;
import com.coopfin.backend.repository.AportacionRepository;
import com.coopfin.backend.repository.ConfiguracionCooperativaRepository;
import com.coopfin.backend.repository.SocioRepository;
import com.coopfin.backend.service.AportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AportacionServiceImpl implements AportacionService {

    private final AportacionRepository aportacionRepository;
    private final SocioRepository socioRepository;
    private final ConfiguracionCooperativaRepository configuracionRepository;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR','SOCIO')")
    @Override
    public AportacionResponse registrar(AportacionRequest request) {

        Socio socio = socioRepository.findById(request.getIdSocio())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + request.getIdSocio()));

        Long idCooperativa = socio.getCooperativa().getIdCooperativa();

        ConfiguracionCooperativa configuracion = configuracionRepository
                .findByCooperativaIdCooperativa(idCooperativa)
                .orElseThrow(() -> new ResourceNotFoundException("La cooperativa no cuenta con configuración financiera"));

        if (request.getMonto().compareTo(configuracion.getMontoMinimoAportacion()) < 0) {
            throw new BadRequestException("El monto mínimo de aportación es S/ " + configuracion.getMontoMinimoAportacion());
        }

        if (request.getMonto().compareTo(configuracion.getMontoMaximoAportacion()) > 0) {
            throw new BadRequestException("El monto máximo de aportación es S/ " + configuracion.getMontoMaximoAportacion());
        }

        int diaActual = LocalDate.now().getDayOfMonth();

        if (diaActual != configuracion.getDiaPagoAportacion()) {
            throw new BadRequestException("La aportación mensual debe realizarse el día " + configuracion.getDiaPagoAportacion() + " de cada mes");
        }

        String periodo = request.getPeriodo() != null && !request.getPeriodo().isBlank()
                ? request.getPeriodo()
                : YearMonth.now().toString();

        Aportacion aportacion = Aportacion.builder()
                .monto(request.getMonto())
                .tipo(request.getTipo())
                .periodo(periodo)
                .observacion(request.getObservacion())
                .socio(socio)
                .build();

        return convertirAResponse(aportacionRepository.save(aportacion));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public List<AportacionResponse> listar() {
        return aportacionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR','SOCIO')")
    @Override
    public List<AportacionResponse> listarPorSocio(Long idSocio) {
        return aportacionRepository.findBySocioIdSocio(idSocio)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private AportacionResponse convertirAResponse(Aportacion aportacion) {
        return AportacionResponse.builder()
                .idAportacion(aportacion.getIdAportacion())
                .monto(aportacion.getMonto())
                .tipo(aportacion.getTipo())
                .periodo(aportacion.getPeriodo())
                .fecha(aportacion.getFecha())
                .observacion(aportacion.getObservacion())
                .estado(aportacion.getEstado())
                .idSocio(aportacion.getSocio().getIdSocio())
                .nombreSocio(aportacion.getSocio().getNombres() + " " + aportacion.getSocio().getApellidos())
                .build();
    }
}
package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.ConfiguracionCooperativaRequest;
import com.coopfin.backend.dto.response.ConfiguracionCooperativaResponse;
import com.coopfin.backend.exception.DuplicateResourceException;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.ConfiguracionCooperativa;
import com.coopfin.backend.model.entity.Cooperativa;
import com.coopfin.backend.repository.ConfiguracionCooperativaRepository;
import com.coopfin.backend.repository.CooperativaRepository;
import com.coopfin.backend.service.ConfiguracionCooperativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfiguracionCooperativaServiceImpl implements ConfiguracionCooperativaService {

    private final ConfiguracionCooperativaRepository configuracionRepository;
    private final CooperativaRepository cooperativaRepository;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Override
    public ConfiguracionCooperativaResponse crear(ConfiguracionCooperativaRequest request) {
        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        if (configuracionRepository.findByCooperativaIdCooperativa(request.getIdCooperativa()).isPresent()) {
            throw new DuplicateResourceException("La cooperativa ya cuenta con una configuración registrada");
        }

        ConfiguracionCooperativa configuracion = ConfiguracionCooperativa.builder()
                .tasaInteresDefault(request.getTasaInteresDefault())
                .moneda(request.getMoneda())
                .montoMaximoPrestamo(request.getMontoMaximoPrestamo())
                .numeroMaximoCuotas(request.getNumeroMaximoCuotas())
                .diasGracia(request.getDiasGracia())
                .estado(true)
                .cooperativa(cooperativa)
                .montoMinimoAportacion(request.getMontoMinimoAportacion())
                .montoMaximoAportacion(request.getMontoMaximoAportacion())
                .diaPagoAportacion(request.getDiaPagoAportacion())
                .build();

        return convertirAResponse(configuracionRepository.save(configuracion));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public List<ConfiguracionCooperativaResponse> listar() {
        return configuracionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public ConfiguracionCooperativaResponse obtenerPorId(Long id) {
        ConfiguracionCooperativa configuracion = configuracionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración no encontrada con id: " + id));

        return convertirAResponse(configuracion);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public ConfiguracionCooperativaResponse obtenerPorCooperativa(Long idCooperativa) {
        ConfiguracionCooperativa configuracion = configuracionRepository.findByCooperativaIdCooperativa(idCooperativa)
                .orElseThrow(() -> new ResourceNotFoundException("No existe configuración para la cooperativa con id: " + idCooperativa));

        return convertirAResponse(configuracion);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Override
    public ConfiguracionCooperativaResponse actualizar(Long id, ConfiguracionCooperativaRequest request) {
        ConfiguracionCooperativa configuracion = configuracionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración no encontrada con id: " + id));

        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        configuracion.setTasaInteresDefault(request.getTasaInteresDefault());
        configuracion.setMoneda(request.getMoneda());
        configuracion.setMontoMaximoPrestamo(request.getMontoMaximoPrestamo());
        configuracion.setNumeroMaximoCuotas(request.getNumeroMaximoCuotas());
        configuracion.setDiasGracia(request.getDiasGracia());
        configuracion.setCooperativa(cooperativa);
        configuracion.setMontoMinimoAportacion(request.getMontoMinimoAportacion());
        configuracion.setMontoMaximoAportacion(request.getMontoMaximoAportacion());
        configuracion.setDiaPagoAportacion(request.getDiaPagoAportacion());

        return convertirAResponse(configuracionRepository.save(configuracion));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @Override
    public void eliminar(Long id) {
        ConfiguracionCooperativa configuracion = configuracionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Configuración no encontrada con id: " + id));

        configuracionRepository.delete(configuracion);
    }

    private ConfiguracionCooperativaResponse convertirAResponse(ConfiguracionCooperativa configuracion) {
        return ConfiguracionCooperativaResponse.builder()
                .idConfiguracion(configuracion.getIdConfiguracion())
                .tasaInteresDefault(configuracion.getTasaInteresDefault())
                .moneda(configuracion.getMoneda())
                .montoMaximoPrestamo(configuracion.getMontoMaximoPrestamo())
                .numeroMaximoCuotas(configuracion.getNumeroMaximoCuotas())
                .diasGracia(configuracion.getDiasGracia())
                .estado(configuracion.getEstado())
                .idCooperativa(configuracion.getCooperativa().getIdCooperativa())
                .nombreCooperativa(configuracion.getCooperativa().getNombre())
                .montoMinimoAportacion(configuracion.getMontoMinimoAportacion())
                .montoMaximoAportacion(configuracion.getMontoMaximoAportacion())
                .diaPagoAportacion(configuracion.getDiaPagoAportacion())
                .build();
    }
}

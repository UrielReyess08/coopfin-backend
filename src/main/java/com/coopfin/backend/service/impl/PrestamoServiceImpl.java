package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.PrestamoSolicitudRequest;
import com.coopfin.backend.dto.response.PrestamoResponse;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.ConfiguracionCooperativa;
import com.coopfin.backend.model.entity.Prestamo;
import com.coopfin.backend.model.entity.Socio;
import com.coopfin.backend.model.enums.EstadoPrestamo;
import com.coopfin.backend.repository.ConfiguracionCooperativaRepository;
import com.coopfin.backend.repository.PrestamoRepository;
import com.coopfin.backend.repository.SocioRepository;
import com.coopfin.backend.service.PrestamoService;
import com.coopfin.backend.model.entity.CuotaPrestamo;
import com.coopfin.backend.model.enums.EstadoCuota;
import com.coopfin.backend.repository.CuotaPrestamoRepository;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;
    private final ConfiguracionCooperativaRepository configuracionRepository;
    private final CuotaPrestamoRepository cuotaPrestamoRepository;

    @Override
    public PrestamoResponse solicitar(PrestamoSolicitudRequest request) {
        Socio socio = socioRepository.findById(request.getIdSocio())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + request.getIdSocio()));

        Long idCooperativa = socio.getCooperativa().getIdCooperativa();

        ConfiguracionCooperativa configuracion = configuracionRepository
                .findByCooperativaIdCooperativa(idCooperativa)
                .orElseThrow(() -> new ResourceNotFoundException("La cooperativa no cuenta con configuración financiera"));

        if (request.getMontoSolicitado().compareTo(configuracion.getMontoMaximoPrestamo()) > 0) {
            throw new RuntimeException("El monto solicitado supera el máximo permitido por la cooperativa");
        }

        if (request.getNumeroCuotas() > configuracion.getNumeroMaximoCuotas()) {
            throw new RuntimeException("El número de cuotas supera el máximo permitido por la cooperativa");
        }

        BigDecimal tasa = configuracion.getTasaInteresDefault();

        Prestamo prestamo = Prestamo.builder()
                .montoSolicitado(request.getMontoSolicitado())
                .numeroCuotas(request.getNumeroCuotas())
                .tasaInteresAplicada(tasa)
                .saldoCapital(BigDecimal.ZERO)
                .saldoInteres(BigDecimal.ZERO)
                .saldoTotal(BigDecimal.ZERO)
                .motivo(request.getMotivo())
                .socio(socio)
                .build();

        return convertirAResponse(prestamoRepository.save(prestamo));
    }

    @Override
    public PrestamoResponse aprobar(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado con id: " + idPrestamo));

        prestamo.setEstado(EstadoPrestamo.APROBADO);
        prestamo.setFechaAprobacion(LocalDate.now());

        BigDecimal interes = prestamo.getMontoSolicitado()
                .multiply(prestamo.getTasaInteresAplicada())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal total = prestamo.getMontoSolicitado().add(interes);

        prestamo.setSaldoCapital(prestamo.getMontoSolicitado());
        prestamo.setSaldoInteres(interes);
        prestamo.setSaldoTotal(total);

        prestamo = prestamoRepository.save(prestamo);

        generarCuotas(prestamo);

        return convertirAResponse(prestamoRepository.save(prestamo));
    }

    @Override
    public List<PrestamoResponse> listar() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public PrestamoResponse obtenerPorId(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado con id: " + id));

        return convertirAResponse(prestamo);
    }

    @Override
    public List<PrestamoResponse> listarPorSocio(Long idSocio) {
        return prestamoRepository.findBySocioIdSocio(idSocio)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public List<PrestamoResponse> listarPorEstado(EstadoPrestamo estado) {
        return prestamoRepository.findByEstado(estado)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private PrestamoResponse convertirAResponse(Prestamo prestamo) {
        return PrestamoResponse.builder()
                .idPrestamo(prestamo.getIdPrestamo())
                .montoSolicitado(prestamo.getMontoSolicitado())
                .numeroCuotas(prestamo.getNumeroCuotas())
                .tasaInteresAplicada(prestamo.getTasaInteresAplicada())
                .saldoCapital(prestamo.getSaldoCapital())
                .saldoInteres(prestamo.getSaldoInteres())
                .saldoTotal(prestamo.getSaldoTotal())
                .fechaSolicitud(prestamo.getFechaSolicitud())
                .fechaAprobacion(prestamo.getFechaAprobacion())
                .motivo(prestamo.getMotivo())
                .estado(prestamo.getEstado())
                .idSocio(prestamo.getSocio().getIdSocio())
                .nombreSocio(prestamo.getSocio().getNombres() + " " + prestamo.getSocio().getApellidos())
                .build();
    }

    private void generarCuotas(Prestamo prestamo) {
        BigDecimal capitalPorCuota = prestamo.getSaldoCapital()
                .divide(BigDecimal.valueOf(prestamo.getNumeroCuotas()), 2, RoundingMode.HALF_UP);

        BigDecimal interesPorCuota = prestamo.getSaldoInteres()
                .divide(BigDecimal.valueOf(prestamo.getNumeroCuotas()), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= prestamo.getNumeroCuotas(); i++) {
            CuotaPrestamo cuota = CuotaPrestamo.builder()
                    .numeroCuota(i)
                    .capitalProgramado(capitalPorCuota)
                    .interesProgramado(interesPorCuota)
                    .montoTotal(capitalPorCuota.add(interesPorCuota))
                    .fechaVencimiento(prestamo.getFechaAprobacion().plusMonths(i))
                    .estado(EstadoCuota.PENDIENTE)
                    .prestamo(prestamo)
                    .build();

            cuotaPrestamoRepository.save(cuota);
        }
    }
}
package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.PagoCuotaRequest;
import com.coopfin.backend.dto.response.PagoCuotaResponse;
import com.coopfin.backend.exception.BadRequestException;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.CuotaPrestamo;
import com.coopfin.backend.model.entity.PagoCuota;
import com.coopfin.backend.model.entity.Prestamo;
import com.coopfin.backend.model.enums.EstadoCuota;
import com.coopfin.backend.model.enums.EstadoPrestamo;
import com.coopfin.backend.repository.CuotaPrestamoRepository;
import com.coopfin.backend.repository.PagoCuotaRepository;
import com.coopfin.backend.repository.PrestamoRepository;
import com.coopfin.backend.service.PagoCuotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoCuotaServiceImpl implements PagoCuotaService {

    private final PagoCuotaRepository pagoCuotaRepository;
    private final CuotaPrestamoRepository cuotaPrestamoRepository;
    private final PrestamoRepository prestamoRepository;

    @Override
    public PagoCuotaResponse registrarPago(PagoCuotaRequest request) {
        CuotaPrestamo cuota = cuotaPrestamoRepository.findById(request.getIdCuota())
                .orElseThrow(() -> new ResourceNotFoundException("Cuota no encontrada con id: " + request.getIdCuota()));

        Prestamo prestamo = cuota.getPrestamo();

        boolean existenCuotasPendientesAnteriores =
                cuotaPrestamoRepository.existsByPrestamoIdPrestamoAndNumeroCuotaLessThanAndEstado(
                        prestamo.getIdPrestamo(),
                        cuota.getNumeroCuota(),
                        EstadoCuota.PENDIENTE
                );

        if (existenCuotasPendientesAnteriores) {
            throw new BadRequestException("No puede pagar esta cuota porque existen cuotas anteriores pendientes");
        }

        if (cuota.getEstado() == EstadoCuota.PAGADA) {
            throw new BadRequestException("La cuota ya se encuentra pagada");
        }

        BigDecimal montoTotalPagado = request.getCapitalPagado()
                .add(request.getInteresPagado())
                .add(request.getMoraPagada());

        if (montoTotalPagado.compareTo(cuota.getMontoTotal()) != 0) {
            throw new RuntimeException("El monto pagado no coincide con el monto total de la cuota");
        }

        PagoCuota pago = PagoCuota.builder()
                .capitalPagado(request.getCapitalPagado())
                .interesPagado(request.getInteresPagado())
                .moraPagada(request.getMoraPagada())
                .montoTotalPagado(montoTotalPagado)
                .metodoPago(request.getMetodoPago())
                .observacion(request.getObservacion())
                .cuota(cuota)
                .build();

        PagoCuota pagoGuardado = pagoCuotaRepository.save(pago);

        cuota.setEstado(EstadoCuota.PAGADA);
        cuotaPrestamoRepository.save(cuota);

        prestamo.setSaldoCapital(prestamo.getSaldoCapital().subtract(request.getCapitalPagado()));
        prestamo.setSaldoInteres(prestamo.getSaldoInteres().subtract(request.getInteresPagado()));
        prestamo.setSaldoTotal(prestamo.getSaldoTotal().subtract(montoTotalPagado));

        boolean todasPagadas = cuotaPrestamoRepository.findByPrestamoIdPrestamo(prestamo.getIdPrestamo())
                .stream()
                .allMatch(c -> c.getEstado() == EstadoCuota.PAGADA);

        if (todasPagadas) {
            prestamo.setEstado(EstadoPrestamo.FINALIZADO);
        }

        prestamoRepository.save(prestamo);

        return convertirAResponse(pagoGuardado);
    }

    @Override
    public List<PagoCuotaResponse> listar() {
        return pagoCuotaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public List<PagoCuotaResponse> listarPorCuota(Long idCuota) {
        return pagoCuotaRepository.findByCuotaIdCuota(idCuota)
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private PagoCuotaResponse convertirAResponse(PagoCuota pago) {
        return PagoCuotaResponse.builder()
                .idPagoCuota(pago.getIdPagoCuota())
                .capitalPagado(pago.getCapitalPagado())
                .interesPagado(pago.getInteresPagado())
                .moraPagada(pago.getMoraPagada())
                .montoTotalPagado(pago.getMontoTotalPagado())
                .fechaPago(pago.getFechaPago())
                .metodoPago(pago.getMetodoPago())
                .observacion(pago.getObservacion())
                .idCuota(pago.getCuota().getIdCuota())
                .numeroCuota(pago.getCuota().getNumeroCuota())
                .idPrestamo(pago.getCuota().getPrestamo().getIdPrestamo())
                .build();
    }
}
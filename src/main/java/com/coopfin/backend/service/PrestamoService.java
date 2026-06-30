package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.PrestamoSolicitudRequest;
import com.coopfin.backend.dto.response.PrestamoResponse;
import com.coopfin.backend.model.enums.EstadoPrestamo;

import java.util.List;

public interface PrestamoService {

    PrestamoResponse solicitar(PrestamoSolicitudRequest request);

    PrestamoResponse aprobar(Long idPrestamo);

    List<PrestamoResponse> listar();

    PrestamoResponse obtenerPorId(Long id);

    List<PrestamoResponse> listarPorSocio(Long idSocio);

    List<PrestamoResponse> listarPorEstado(EstadoPrestamo estado);
}

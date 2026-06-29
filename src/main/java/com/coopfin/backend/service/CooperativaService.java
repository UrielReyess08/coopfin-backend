package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.CooperativaRequest;
import com.coopfin.backend.dto.response.CooperativaResponse;

import java.util.List;

public interface CooperativaService {

    CooperativaResponse crear(CooperativaRequest request);

    List<CooperativaResponse> listar();

    CooperativaResponse obtenerPorId(Long id);

    CooperativaResponse actualizar(Long id, CooperativaRequest request);

    void eliminar(Long id);
}

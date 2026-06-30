package com.coopfin.backend.service;

import com.coopfin.backend.dto.response.HistorialFinancieroResponse;

public interface HistorialFinancieroService {

    HistorialFinancieroResponse obtenerPorSocio(Long idSocio);
}
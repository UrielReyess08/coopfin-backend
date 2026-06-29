package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.ConfiguracionCooperativaRequest;
import com.coopfin.backend.dto.response.ConfiguracionCooperativaResponse;

import java.util.List;

public interface ConfiguracionCooperativaService {

    ConfiguracionCooperativaResponse crear(ConfiguracionCooperativaRequest request);

    List<ConfiguracionCooperativaResponse> listar();

    ConfiguracionCooperativaResponse obtenerPorId(Long id);

    ConfiguracionCooperativaResponse obtenerPorCooperativa(Long idCooperativa);

    ConfiguracionCooperativaResponse actualizar(Long id, ConfiguracionCooperativaRequest request);

    void eliminar(Long id);
}

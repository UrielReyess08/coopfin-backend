package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.AportacionRequest;
import com.coopfin.backend.dto.response.AportacionResponse;

import java.util.List;

public interface AportacionService {

    AportacionResponse registrar(AportacionRequest request);

    List<AportacionResponse> listar();

    List<AportacionResponse> listarPorSocio(Long idSocio);
}
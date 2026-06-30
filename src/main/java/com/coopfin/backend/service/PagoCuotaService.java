package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.PagoCuotaRequest;
import com.coopfin.backend.dto.response.PagoCuotaResponse;

import java.util.List;

public interface PagoCuotaService {

    PagoCuotaResponse registrarPago(PagoCuotaRequest request);

    List<PagoCuotaResponse> listar();

    List<PagoCuotaResponse> listarPorCuota(Long idCuota);
}
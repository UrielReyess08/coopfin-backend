package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.SocioRequest;
import com.coopfin.backend.dto.response.SocioResponse;

import java.util.List;

public interface SocioService {

    SocioResponse crear(SocioRequest request);

    List<SocioResponse> listar();

    SocioResponse obtenerPorId(Long id);

    SocioResponse actualizar(Long id, SocioRequest request);

    void eliminar(Long id);
}

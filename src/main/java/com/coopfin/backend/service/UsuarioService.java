package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.UsuarioRequest;
import com.coopfin.backend.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse crear(UsuarioRequest request);

    List<UsuarioResponse> listar();

    UsuarioResponse obtenerPorId(Long id);

    UsuarioResponse actualizar(Long id, UsuarioRequest request);

    void eliminar(Long id);
}

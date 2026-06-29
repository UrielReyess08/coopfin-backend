package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.RolRequest;
import com.coopfin.backend.dto.response.RolResponse;
import java.util.List;

public interface RolService {

    RolResponse crearRol(RolRequest request);

    List<RolResponse> listarRoles();

    RolResponse obtenerRolPorId(Long id);

    RolResponse actualizarRol(Long id, RolRequest request);

    void eliminarRol(Long id);
}

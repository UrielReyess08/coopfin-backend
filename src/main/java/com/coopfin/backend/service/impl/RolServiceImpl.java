package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.RolRequest;
import com.coopfin.backend.dto.response.RolResponse;
import com.coopfin.backend.model.entity.Rol;
import com.coopfin.backend.repository.RolRepository;
import com.coopfin.backend.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public RolResponse crearRol(RolRequest request) {
        Rol rol = Rol.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();

        return convertirAResponse(rolRepository.save(rol));
    }

    @Override
    public RolResponse obtenerRolPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        return convertirAResponse(rol);
    }

    @Override
    public RolResponse actualizarRol(Long id, RolRequest request) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));

        rol.setNombre(request.getNombre());
        rol.setDescripcion(request.getDescripcion());

        return convertirAResponse(rolRepository.save(rol));
    }

    @Override
    public List<RolResponse> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public void eliminarRol(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        rolRepository.delete(rol);
    }

    private RolResponse convertirAResponse(Rol rol) {
        return RolResponse.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .build();
    }
}

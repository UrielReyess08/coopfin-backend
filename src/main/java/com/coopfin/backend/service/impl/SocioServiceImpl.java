package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.SocioRequest;
import com.coopfin.backend.dto.response.SocioResponse;
import com.coopfin.backend.exception.DuplicateResourceException;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.Cooperativa;
import com.coopfin.backend.model.entity.Socio;
import com.coopfin.backend.model.entity.Usuario;
import com.coopfin.backend.repository.CooperativaRepository;
import com.coopfin.backend.repository.SocioRepository;
import com.coopfin.backend.repository.UsuarioRepository;
import com.coopfin.backend.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioServiceImpl implements SocioService {

    private final SocioRepository socioRepository;
    private final CooperativaRepository cooperativaRepository;
    private final UsuarioRepository usuarioRepository;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public SocioResponse crear(SocioRequest request) {
        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        if (socioRepository.findByCodigoSocioAndCooperativaIdCooperativa(request.getCodigoSocio(), request.getIdCooperativa()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un socio con el código: " + request.getCodigoSocio());
        }

        if (socioRepository.findByDniAndCooperativaIdCooperativa(request.getDni(), request.getIdCooperativa()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un socio con el DNI: " + request.getDni());
        }

        Usuario usuario = null;
        if (request.getIdUsuario() != null) {
            usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + request.getIdUsuario()));
        }

        Socio socio = Socio.builder()
                .codigoSocio(request.getCodigoSocio())
                .dni(request.getDni())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .usuario(usuario)
                .cooperativa(cooperativa)
                .build();

        return convertirAResponse(socioRepository.save(socio));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public List<SocioResponse> listar() {
        return socioRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public SocioResponse obtenerPorId(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + id));

        return convertirAResponse(socio);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public SocioResponse actualizar(Long id, SocioRequest request) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + id));

        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        Usuario usuario = null;
        if (request.getIdUsuario() != null) {
            usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + request.getIdUsuario()));
        }

        socio.setCodigoSocio(request.getCodigoSocio());
        socio.setDni(request.getDni());
        socio.setNombres(request.getNombres());
        socio.setApellidos(request.getApellidos());
        socio.setTelefono(request.getTelefono());
        socio.setDireccion(request.getDireccion());
        socio.setUsuario(usuario);
        socio.setCooperativa(cooperativa);

        return convertirAResponse(socioRepository.save(socio));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @Override
    public void eliminar(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con id: " + id));

        socioRepository.delete(socio);
    }

    private SocioResponse convertirAResponse(Socio socio) {
        return SocioResponse.builder()
                .idSocio(socio.getIdSocio())
                .codigoSocio(socio.getCodigoSocio())
                .dni(socio.getDni())
                .nombres(socio.getNombres())
                .apellidos(socio.getApellidos())
                .telefono(socio.getTelefono())
                .direccion(socio.getDireccion())
                .fechaIngreso(socio.getFechaIngreso())
                .estado(socio.getEstado())
                .idUsuario(socio.getUsuario() != null ? socio.getUsuario().getIdUsuario() : null)
                .username(socio.getUsuario() != null ? socio.getUsuario().getUsername() : null)
                .idCooperativa(socio.getCooperativa().getIdCooperativa())
                .nombreCooperativa(socio.getCooperativa().getNombre())
                .build();
    }
}

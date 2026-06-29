package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.UsuarioRequest;
import com.coopfin.backend.dto.response.UsuarioResponse;
import com.coopfin.backend.exception.DuplicateResourceException;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.Cooperativa;
import com.coopfin.backend.model.entity.Rol;
import com.coopfin.backend.model.entity.Usuario;
import com.coopfin.backend.repository.CooperativaRepository;
import com.coopfin.backend.repository.RolRepository;
import com.coopfin.backend.repository.UsuarioRepository;
import com.coopfin.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final CooperativaRepository cooperativaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse crear(UsuarioRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un usuario con el username: " + request.getUsername());
        }

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un usuario con el correo: " + request.getEmail());
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + request.getIdRol()));

        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .rol(rol)
                .cooperativa(cooperativa)
                .build();

        return convertirAResponse(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        return convertirAResponse(usuario);
    }

    @Override
    public UsuarioResponse actualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + request.getIdRol()));

        Cooperativa cooperativa = cooperativaRepository.findById(request.getIdCooperativa())
                .orElseThrow(() -> new ResourceNotFoundException("Cooperativa no encontrada con id: " + request.getIdCooperativa()));

        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEmail(request.getEmail());
        usuario.setRol(rol);
        usuario.setCooperativa(cooperativa);

        return convertirAResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        usuarioRepository.delete(usuario);
    }

    private UsuarioResponse convertirAResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .estado(usuario.getEstado())
                .ultimoLogin(usuario.getUltimoLogin())
                .fechaCreacion(usuario.getFechaCreacion())
                .idRol(usuario.getRol().getIdRol())
                .nombreRol(usuario.getRol().getNombre())
                .idCooperativa(usuario.getCooperativa().getIdCooperativa())
                .nombreCooperativa(usuario.getCooperativa().getNombre())
                .build();
    }
}

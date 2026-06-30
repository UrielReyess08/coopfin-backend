package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.AuthRequest;
import com.coopfin.backend.dto.response.AuthResponse;
import com.coopfin.backend.exception.ResourceNotFoundException;
import com.coopfin.backend.model.entity.Usuario;
import com.coopfin.backend.repository.UsuarioRepository;
import com.coopfin.backend.security.JwtService;
import com.coopfin.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String token = jwtService.generarToken(usuario.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(usuario.getUsername())
                .rol(usuario.getRol().getNombre())
                .idCooperativa(usuario.getCooperativa().getIdCooperativa())
                .nombreCooperativa(usuario.getCooperativa().getNombre())
                .build();
    }
}
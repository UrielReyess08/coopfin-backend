package com.coopfin.backend.service.impl;

import com.coopfin.backend.dto.request.CooperativaRequest;
import com.coopfin.backend.dto.response.CooperativaResponse;
import com.coopfin.backend.model.entity.Cooperativa;
import com.coopfin.backend.repository.CooperativaRepository;
import com.coopfin.backend.service.CooperativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CooperativaServiceImpl implements CooperativaService {

    private  final CooperativaRepository cooperativaRepository;

    @Override
    public CooperativaResponse crear(CooperativaRequest request) {
        Cooperativa cooperativa = Cooperativa.builder()
                .nombre(request.getNombre())
                .ruc(request.getRuc())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .logoUrl(request.getLogoUrl())
                .colorPrincipal(request.getColorPrincipal())
                .colorSecundario(request.getColorSecundario())
                .estado(true)
                .build();

        return convertirAResponse(cooperativaRepository.save(cooperativa));
    }

    @Override
    public List<CooperativaResponse> listar() {
        return cooperativaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    @Override
    public CooperativaResponse obtenerPorId(Long id) {
        Cooperativa cooperativa = cooperativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cooperativa no encontrada con id: " + id));

        return convertirAResponse(cooperativa);
    }

    @Override
    public CooperativaResponse actualizar(Long id, CooperativaRequest request) {
        Cooperativa cooperativa = cooperativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cooperativa no encontrada con id: " + id));

        cooperativa.setNombre(request.getNombre());
        cooperativa.setRuc(request.getRuc());
        cooperativa.setDireccion(request.getDireccion());
        cooperativa.setTelefono(request.getTelefono());
        cooperativa.setEmail(request.getEmail());
        cooperativa.setLogoUrl(request.getLogoUrl());
        cooperativa.setColorPrincipal(request.getColorPrincipal());
        cooperativa.setColorSecundario(request.getColorSecundario());

        return convertirAResponse(cooperativaRepository.save(cooperativa));
    }

    @Override
    public void eliminar(Long id) {
        Cooperativa cooperativa = cooperativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cooperativa no encontrada con id: " + id));

        cooperativaRepository.delete(cooperativa);
    }

    private CooperativaResponse convertirAResponse(Cooperativa cooperativa) {
        return CooperativaResponse.builder()
                .idCooperativa(cooperativa.getIdCooperativa())
                .nombre(cooperativa.getNombre())
                .ruc(cooperativa.getRuc())
                .direccion(cooperativa.getDireccion())
                .telefono(cooperativa.getTelefono())
                .email(cooperativa.getEmail())
                .logoUrl(cooperativa.getLogoUrl())
                .colorPrincipal(cooperativa.getColorPrincipal())
                .colorSecundario(cooperativa.getColorSecundario())
                .estado(cooperativa.getEstado())
                .build();
    }
}

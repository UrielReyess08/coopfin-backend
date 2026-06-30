package com.coopfin.backend.service;

import com.coopfin.backend.dto.response.CuotaPrestamoResponse;

import java.util.List;

public interface CuotaPrestamoService {

    List<CuotaPrestamoResponse> listarPorPrestamo(Long idPrestamo);

}
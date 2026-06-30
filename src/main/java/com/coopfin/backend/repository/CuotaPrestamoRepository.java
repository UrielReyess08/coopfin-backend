package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.CuotaPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuotaPrestamoRepository extends JpaRepository<CuotaPrestamo, Long> {

    List<CuotaPrestamo> findByPrestamoIdPrestamo(Long idPrestamo);
}

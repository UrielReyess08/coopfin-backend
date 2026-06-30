package com.coopfin.backend.repository;

import com.coopfin.backend.model.enums.EstadoCuota;
import com.coopfin.backend.model.entity.CuotaPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuotaPrestamoRepository extends JpaRepository<CuotaPrestamo, Long> {

    boolean existsByPrestamoIdPrestamoAndNumeroCuotaLessThanAndEstado(
            Long idPrestamo,
            Integer numeroCuota,
            EstadoCuota estado
    );

    List<CuotaPrestamo> findByPrestamoIdPrestamo(Long idPrestamo);
}

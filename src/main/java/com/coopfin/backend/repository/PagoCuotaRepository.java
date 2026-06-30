package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.PagoCuota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoCuotaRepository extends JpaRepository<PagoCuota, Long> {

    List<PagoCuota> findByCuotaIdCuota(Long idCuota);
}
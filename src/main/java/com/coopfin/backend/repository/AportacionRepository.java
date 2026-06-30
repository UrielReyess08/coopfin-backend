package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.Aportacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AportacionRepository extends JpaRepository<Aportacion, Long> {

    List<Aportacion> findBySocioIdSocio(Long idSocio);
}
package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.Prestamo;
import com.coopfin.backend.model.enums.EstadoPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findBySocioIdSocio(Long idSocio);

    List<Prestamo> findByEstado(EstadoPrestamo estado);
}

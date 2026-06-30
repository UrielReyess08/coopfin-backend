package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    Optional<Socio> findByCodigoSocioAndCooperativaIdCooperativa(String codigoSocio, Long idCooperativa);

    Optional<Socio> findByDniAndCooperativaIdCooperativa(String dni, Long idCooperativa);
}

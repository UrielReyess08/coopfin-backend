package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.ConfiguracionCooperativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracionCooperativaRepository extends JpaRepository<ConfiguracionCooperativa, Long> {

    Optional<ConfiguracionCooperativa> findByCooperativaIdCooperativa(Long idCooperativa);
}

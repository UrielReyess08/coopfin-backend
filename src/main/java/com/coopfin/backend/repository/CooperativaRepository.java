package com.coopfin.backend.repository;

import com.coopfin.backend.model.entity.Cooperativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CooperativaRepository extends JpaRepository<Cooperativa, Long> {

    Optional<Cooperativa> findByRuc(String ruc);
}

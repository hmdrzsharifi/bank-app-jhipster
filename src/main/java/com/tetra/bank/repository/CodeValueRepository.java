package com.tetra.bank.repository;

import com.tetra.bank.domain.CodeValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CodeValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodeValueRepository extends JpaRepository<CodeValue, Long> {
}

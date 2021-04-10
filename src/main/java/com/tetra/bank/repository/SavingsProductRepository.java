package com.tetra.bank.repository;

import com.tetra.bank.domain.SavingsProduct;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SavingsProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsProductRepository extends JpaRepository<SavingsProduct, Long> {
}

package com.tetra.bank.repository;

import com.tetra.bank.domain.SavingsAccountTransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SavingsAccountTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsAccountTransactionRepository extends JpaRepository<SavingsAccountTransaction, Long> {
}

package com.tetra.bank.repository;

import com.tetra.bank.domain.SavingsAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SavingsAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}

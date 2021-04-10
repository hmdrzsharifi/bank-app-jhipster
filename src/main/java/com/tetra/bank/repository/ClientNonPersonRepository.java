package com.tetra.bank.repository;

import com.tetra.bank.domain.ClientNonPerson;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ClientNonPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientNonPersonRepository extends JpaRepository<ClientNonPerson, Long> {
}

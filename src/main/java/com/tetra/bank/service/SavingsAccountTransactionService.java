package com.tetra.bank.service;

import com.tetra.bank.domain.SavingsAccountTransaction;
import com.tetra.bank.repository.SavingsAccountTransactionRepository;
import com.tetra.bank.service.dto.SavingsAccountTransactionDTO;
import com.tetra.bank.service.mapper.SavingsAccountTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SavingsAccountTransaction}.
 */
@Service
@Transactional
public class SavingsAccountTransactionService {

    private final Logger log = LoggerFactory.getLogger(SavingsAccountTransactionService.class);

    private final SavingsAccountTransactionRepository savingsAccountTransactionRepository;

    private final SavingsAccountTransactionMapper savingsAccountTransactionMapper;

    public SavingsAccountTransactionService(SavingsAccountTransactionRepository savingsAccountTransactionRepository, SavingsAccountTransactionMapper savingsAccountTransactionMapper) {
        this.savingsAccountTransactionRepository = savingsAccountTransactionRepository;
        this.savingsAccountTransactionMapper = savingsAccountTransactionMapper;
    }

    /**
     * Save a savingsAccountTransaction.
     *
     * @param savingsAccountTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    public SavingsAccountTransactionDTO save(SavingsAccountTransactionDTO savingsAccountTransactionDTO) {
        log.debug("Request to save SavingsAccountTransaction : {}", savingsAccountTransactionDTO);
        SavingsAccountTransaction savingsAccountTransaction = savingsAccountTransactionMapper.toEntity(savingsAccountTransactionDTO);
        savingsAccountTransaction = savingsAccountTransactionRepository.save(savingsAccountTransaction);
        return savingsAccountTransactionMapper.toDto(savingsAccountTransaction);
    }

    /**
     * Get all the savingsAccountTransactions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SavingsAccountTransactionDTO> findAll() {
        log.debug("Request to get all SavingsAccountTransactions");
        return savingsAccountTransactionRepository.findAll().stream()
            .map(savingsAccountTransactionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one savingsAccountTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SavingsAccountTransactionDTO> findOne(Long id) {
        log.debug("Request to get SavingsAccountTransaction : {}", id);
        return savingsAccountTransactionRepository.findById(id)
            .map(savingsAccountTransactionMapper::toDto);
    }

    /**
     * Delete the savingsAccountTransaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SavingsAccountTransaction : {}", id);
        savingsAccountTransactionRepository.deleteById(id);
    }
}

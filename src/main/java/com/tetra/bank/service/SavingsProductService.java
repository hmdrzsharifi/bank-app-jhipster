package com.tetra.bank.service;

import com.tetra.bank.domain.SavingsProduct;
import com.tetra.bank.repository.SavingsProductRepository;
import com.tetra.bank.service.dto.SavingsProductDTO;
import com.tetra.bank.service.mapper.SavingsProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SavingsProduct}.
 */
@Service
@Transactional
public class SavingsProductService {

    private final Logger log = LoggerFactory.getLogger(SavingsProductService.class);

    private final SavingsProductRepository savingsProductRepository;

    private final SavingsProductMapper savingsProductMapper;

    public SavingsProductService(SavingsProductRepository savingsProductRepository, SavingsProductMapper savingsProductMapper) {
        this.savingsProductRepository = savingsProductRepository;
        this.savingsProductMapper = savingsProductMapper;
    }

    /**
     * Save a savingsProduct.
     *
     * @param savingsProductDTO the entity to save.
     * @return the persisted entity.
     */
    public SavingsProductDTO save(SavingsProductDTO savingsProductDTO) {
        log.debug("Request to save SavingsProduct : {}", savingsProductDTO);
        SavingsProduct savingsProduct = savingsProductMapper.toEntity(savingsProductDTO);
        savingsProduct = savingsProductRepository.save(savingsProduct);
        return savingsProductMapper.toDto(savingsProduct);
    }

    /**
     * Get all the savingsProducts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SavingsProductDTO> findAll() {
        log.debug("Request to get all SavingsProducts");
        return savingsProductRepository.findAll().stream()
            .map(savingsProductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one savingsProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SavingsProductDTO> findOne(Long id) {
        log.debug("Request to get SavingsProduct : {}", id);
        return savingsProductRepository.findById(id)
            .map(savingsProductMapper::toDto);
    }

    /**
     * Delete the savingsProduct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SavingsProduct : {}", id);
        savingsProductRepository.deleteById(id);
    }
}

package com.tetra.bank.service;

import com.tetra.bank.domain.PaymentDetails;
import com.tetra.bank.repository.PaymentDetailsRepository;
import com.tetra.bank.service.dto.PaymentDetailsDTO;
import com.tetra.bank.service.mapper.PaymentDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PaymentDetails}.
 */
@Service
@Transactional
public class PaymentDetailsService {

    private final Logger log = LoggerFactory.getLogger(PaymentDetailsService.class);

    private final PaymentDetailsRepository paymentDetailsRepository;

    private final PaymentDetailsMapper paymentDetailsMapper;

    public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository, PaymentDetailsMapper paymentDetailsMapper) {
        this.paymentDetailsRepository = paymentDetailsRepository;
        this.paymentDetailsMapper = paymentDetailsMapper;
    }

    /**
     * Save a paymentDetails.
     *
     * @param paymentDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentDetailsDTO save(PaymentDetailsDTO paymentDetailsDTO) {
        log.debug("Request to save PaymentDetails : {}", paymentDetailsDTO);
        PaymentDetails paymentDetails = paymentDetailsMapper.toEntity(paymentDetailsDTO);
        paymentDetails = paymentDetailsRepository.save(paymentDetails);
        return paymentDetailsMapper.toDto(paymentDetails);
    }

    /**
     * Get all the paymentDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentDetailsDTO> findAll() {
        log.debug("Request to get all PaymentDetails");
        return paymentDetailsRepository.findAll().stream()
            .map(paymentDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one paymentDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentDetailsDTO> findOne(Long id) {
        log.debug("Request to get PaymentDetails : {}", id);
        return paymentDetailsRepository.findById(id)
            .map(paymentDetailsMapper::toDto);
    }

    /**
     * Delete the paymentDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentDetails : {}", id);
        paymentDetailsRepository.deleteById(id);
    }
}

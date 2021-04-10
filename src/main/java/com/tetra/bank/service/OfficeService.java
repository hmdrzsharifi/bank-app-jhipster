package com.tetra.bank.service;

import com.tetra.bank.domain.Office;
import com.tetra.bank.repository.OfficeRepository;
import com.tetra.bank.service.dto.OfficeDTO;
import com.tetra.bank.service.mapper.OfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Office}.
 */
@Service
@Transactional
public class OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeService.class);

    private final OfficeRepository officeRepository;

    private final OfficeMapper officeMapper;

    public OfficeService(OfficeRepository officeRepository, OfficeMapper officeMapper) {
        this.officeRepository = officeRepository;
        this.officeMapper = officeMapper;
    }

    /**
     * Save a office.
     *
     * @param officeDTO the entity to save.
     * @return the persisted entity.
     */
    public OfficeDTO save(OfficeDTO officeDTO) {
        log.debug("Request to save Office : {}", officeDTO);
        Office office = officeMapper.toEntity(officeDTO);
        office = officeRepository.save(office);
        return officeMapper.toDto(office);
    }

    /**
     * Get all the offices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OfficeDTO> findAll() {
        log.debug("Request to get all Offices");
        return officeRepository.findAll().stream()
            .map(officeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one office by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OfficeDTO> findOne(Long id) {
        log.debug("Request to get Office : {}", id);
        return officeRepository.findById(id)
            .map(officeMapper::toDto);
    }

    /**
     * Delete the office by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Office : {}", id);
        officeRepository.deleteById(id);
    }
}

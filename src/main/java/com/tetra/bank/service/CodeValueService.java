package com.tetra.bank.service;

import com.tetra.bank.domain.CodeValue;
import com.tetra.bank.repository.CodeValueRepository;
import com.tetra.bank.service.dto.CodeValueDTO;
import com.tetra.bank.service.mapper.CodeValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CodeValue}.
 */
@Service
@Transactional
public class CodeValueService {

    private final Logger log = LoggerFactory.getLogger(CodeValueService.class);

    private final CodeValueRepository codeValueRepository;

    private final CodeValueMapper codeValueMapper;

    public CodeValueService(CodeValueRepository codeValueRepository, CodeValueMapper codeValueMapper) {
        this.codeValueRepository = codeValueRepository;
        this.codeValueMapper = codeValueMapper;
    }

    /**
     * Save a codeValue.
     *
     * @param codeValueDTO the entity to save.
     * @return the persisted entity.
     */
    public CodeValueDTO save(CodeValueDTO codeValueDTO) {
        log.debug("Request to save CodeValue : {}", codeValueDTO);
        CodeValue codeValue = codeValueMapper.toEntity(codeValueDTO);
        codeValue = codeValueRepository.save(codeValue);
        return codeValueMapper.toDto(codeValue);
    }

    /**
     * Get all the codeValues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CodeValueDTO> findAll() {
        log.debug("Request to get all CodeValues");
        return codeValueRepository.findAll().stream()
            .map(codeValueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one codeValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CodeValueDTO> findOne(Long id) {
        log.debug("Request to get CodeValue : {}", id);
        return codeValueRepository.findById(id)
            .map(codeValueMapper::toDto);
    }

    /**
     * Delete the codeValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CodeValue : {}", id);
        codeValueRepository.deleteById(id);
    }
}

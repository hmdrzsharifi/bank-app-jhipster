package com.tetra.bank.service;

import com.tetra.bank.domain.ClientNonPerson;
import com.tetra.bank.repository.ClientNonPersonRepository;
import com.tetra.bank.service.dto.ClientNonPersonDTO;
import com.tetra.bank.service.mapper.ClientNonPersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ClientNonPerson}.
 */
@Service
@Transactional
public class ClientNonPersonService {

    private final Logger log = LoggerFactory.getLogger(ClientNonPersonService.class);

    private final ClientNonPersonRepository clientNonPersonRepository;

    private final ClientNonPersonMapper clientNonPersonMapper;

    public ClientNonPersonService(ClientNonPersonRepository clientNonPersonRepository, ClientNonPersonMapper clientNonPersonMapper) {
        this.clientNonPersonRepository = clientNonPersonRepository;
        this.clientNonPersonMapper = clientNonPersonMapper;
    }

    /**
     * Save a clientNonPerson.
     *
     * @param clientNonPersonDTO the entity to save.
     * @return the persisted entity.
     */
    public ClientNonPersonDTO save(ClientNonPersonDTO clientNonPersonDTO) {
        log.debug("Request to save ClientNonPerson : {}", clientNonPersonDTO);
        ClientNonPerson clientNonPerson = clientNonPersonMapper.toEntity(clientNonPersonDTO);
        clientNonPerson = clientNonPersonRepository.save(clientNonPerson);
        return clientNonPersonMapper.toDto(clientNonPerson);
    }

    /**
     * Get all the clientNonPeople.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClientNonPersonDTO> findAll() {
        log.debug("Request to get all ClientNonPeople");
        return clientNonPersonRepository.findAll().stream()
            .map(clientNonPersonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one clientNonPerson by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClientNonPersonDTO> findOne(Long id) {
        log.debug("Request to get ClientNonPerson : {}", id);
        return clientNonPersonRepository.findById(id)
            .map(clientNonPersonMapper::toDto);
    }

    /**
     * Delete the clientNonPerson by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ClientNonPerson : {}", id);
        clientNonPersonRepository.deleteById(id);
    }
}

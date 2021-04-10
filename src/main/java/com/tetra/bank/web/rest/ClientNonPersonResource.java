package com.tetra.bank.web.rest;

import com.tetra.bank.service.ClientNonPersonService;
import com.tetra.bank.web.rest.errors.BadRequestAlertException;
import com.tetra.bank.service.dto.ClientNonPersonDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tetra.bank.domain.ClientNonPerson}.
 */
@RestController
@RequestMapping("/api")
public class ClientNonPersonResource {

    private final Logger log = LoggerFactory.getLogger(ClientNonPersonResource.class);

    private static final String ENTITY_NAME = "clientNonPerson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientNonPersonService clientNonPersonService;

    public ClientNonPersonResource(ClientNonPersonService clientNonPersonService) {
        this.clientNonPersonService = clientNonPersonService;
    }

    /**
     * {@code POST  /client-non-people} : Create a new clientNonPerson.
     *
     * @param clientNonPersonDTO the clientNonPersonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientNonPersonDTO, or with status {@code 400 (Bad Request)} if the clientNonPerson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/client-non-people")
    public ResponseEntity<ClientNonPersonDTO> createClientNonPerson(@RequestBody ClientNonPersonDTO clientNonPersonDTO) throws URISyntaxException {
        log.debug("REST request to save ClientNonPerson : {}", clientNonPersonDTO);
        if (clientNonPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientNonPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientNonPersonDTO result = clientNonPersonService.save(clientNonPersonDTO);
        return ResponseEntity.created(new URI("/api/client-non-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-non-people} : Updates an existing clientNonPerson.
     *
     * @param clientNonPersonDTO the clientNonPersonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientNonPersonDTO,
     * or with status {@code 400 (Bad Request)} if the clientNonPersonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientNonPersonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/client-non-people")
    public ResponseEntity<ClientNonPersonDTO> updateClientNonPerson(@RequestBody ClientNonPersonDTO clientNonPersonDTO) throws URISyntaxException {
        log.debug("REST request to update ClientNonPerson : {}", clientNonPersonDTO);
        if (clientNonPersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClientNonPersonDTO result = clientNonPersonService.save(clientNonPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientNonPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /client-non-people} : get all the clientNonPeople.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientNonPeople in body.
     */
    @GetMapping("/client-non-people")
    public List<ClientNonPersonDTO> getAllClientNonPeople() {
        log.debug("REST request to get all ClientNonPeople");
        return clientNonPersonService.findAll();
    }

    /**
     * {@code GET  /client-non-people/:id} : get the "id" clientNonPerson.
     *
     * @param id the id of the clientNonPersonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientNonPersonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/client-non-people/{id}")
    public ResponseEntity<ClientNonPersonDTO> getClientNonPerson(@PathVariable Long id) {
        log.debug("REST request to get ClientNonPerson : {}", id);
        Optional<ClientNonPersonDTO> clientNonPersonDTO = clientNonPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientNonPersonDTO);
    }

    /**
     * {@code DELETE  /client-non-people/:id} : delete the "id" clientNonPerson.
     *
     * @param id the id of the clientNonPersonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/client-non-people/{id}")
    public ResponseEntity<Void> deleteClientNonPerson(@PathVariable Long id) {
        log.debug("REST request to delete ClientNonPerson : {}", id);
        clientNonPersonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

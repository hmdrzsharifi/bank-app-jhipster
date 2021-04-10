package com.tetra.bank.web.rest;

import com.tetra.bank.service.CodeValueService;
import com.tetra.bank.web.rest.errors.BadRequestAlertException;
import com.tetra.bank.service.dto.CodeValueDTO;

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
 * REST controller for managing {@link com.tetra.bank.domain.CodeValue}.
 */
@RestController
@RequestMapping("/api")
public class CodeValueResource {

    private final Logger log = LoggerFactory.getLogger(CodeValueResource.class);

    private static final String ENTITY_NAME = "codeValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CodeValueService codeValueService;

    public CodeValueResource(CodeValueService codeValueService) {
        this.codeValueService = codeValueService;
    }

    /**
     * {@code POST  /code-values} : Create a new codeValue.
     *
     * @param codeValueDTO the codeValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new codeValueDTO, or with status {@code 400 (Bad Request)} if the codeValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/code-values")
    public ResponseEntity<CodeValueDTO> createCodeValue(@RequestBody CodeValueDTO codeValueDTO) throws URISyntaxException {
        log.debug("REST request to save CodeValue : {}", codeValueDTO);
        if (codeValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new codeValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodeValueDTO result = codeValueService.save(codeValueDTO);
        return ResponseEntity.created(new URI("/api/code-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /code-values} : Updates an existing codeValue.
     *
     * @param codeValueDTO the codeValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated codeValueDTO,
     * or with status {@code 400 (Bad Request)} if the codeValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the codeValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/code-values")
    public ResponseEntity<CodeValueDTO> updateCodeValue(@RequestBody CodeValueDTO codeValueDTO) throws URISyntaxException {
        log.debug("REST request to update CodeValue : {}", codeValueDTO);
        if (codeValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodeValueDTO result = codeValueService.save(codeValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, codeValueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /code-values} : get all the codeValues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of codeValues in body.
     */
    @GetMapping("/code-values")
    public List<CodeValueDTO> getAllCodeValues() {
        log.debug("REST request to get all CodeValues");
        return codeValueService.findAll();
    }

    /**
     * {@code GET  /code-values/:id} : get the "id" codeValue.
     *
     * @param id the id of the codeValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the codeValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/code-values/{id}")
    public ResponseEntity<CodeValueDTO> getCodeValue(@PathVariable Long id) {
        log.debug("REST request to get CodeValue : {}", id);
        Optional<CodeValueDTO> codeValueDTO = codeValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(codeValueDTO);
    }

    /**
     * {@code DELETE  /code-values/:id} : delete the "id" codeValue.
     *
     * @param id the id of the codeValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/code-values/{id}")
    public ResponseEntity<Void> deleteCodeValue(@PathVariable Long id) {
        log.debug("REST request to delete CodeValue : {}", id);
        codeValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

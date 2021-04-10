package com.tetra.bank.web.rest;

import com.tetra.bank.service.SavingsProductService;
import com.tetra.bank.web.rest.errors.BadRequestAlertException;
import com.tetra.bank.service.dto.SavingsProductDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tetra.bank.domain.SavingsProduct}.
 */
@RestController
@RequestMapping("/api")
public class SavingsProductResource {

    private final Logger log = LoggerFactory.getLogger(SavingsProductResource.class);

    private static final String ENTITY_NAME = "savingsProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SavingsProductService savingsProductService;

    public SavingsProductResource(SavingsProductService savingsProductService) {
        this.savingsProductService = savingsProductService;
    }

    /**
     * {@code POST  /savings-products} : Create a new savingsProduct.
     *
     * @param savingsProductDTO the savingsProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new savingsProductDTO, or with status {@code 400 (Bad Request)} if the savingsProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/savings-products")
    public ResponseEntity<SavingsProductDTO> createSavingsProduct(@Valid @RequestBody SavingsProductDTO savingsProductDTO) throws URISyntaxException {
        log.debug("REST request to save SavingsProduct : {}", savingsProductDTO);
        if (savingsProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new savingsProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SavingsProductDTO result = savingsProductService.save(savingsProductDTO);
        return ResponseEntity.created(new URI("/api/savings-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /savings-products} : Updates an existing savingsProduct.
     *
     * @param savingsProductDTO the savingsProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated savingsProductDTO,
     * or with status {@code 400 (Bad Request)} if the savingsProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the savingsProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/savings-products")
    public ResponseEntity<SavingsProductDTO> updateSavingsProduct(@Valid @RequestBody SavingsProductDTO savingsProductDTO) throws URISyntaxException {
        log.debug("REST request to update SavingsProduct : {}", savingsProductDTO);
        if (savingsProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SavingsProductDTO result = savingsProductService.save(savingsProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, savingsProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /savings-products} : get all the savingsProducts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of savingsProducts in body.
     */
    @GetMapping("/savings-products")
    public List<SavingsProductDTO> getAllSavingsProducts() {
        log.debug("REST request to get all SavingsProducts");
        return savingsProductService.findAll();
    }

    /**
     * {@code GET  /savings-products/:id} : get the "id" savingsProduct.
     *
     * @param id the id of the savingsProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the savingsProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/savings-products/{id}")
    public ResponseEntity<SavingsProductDTO> getSavingsProduct(@PathVariable Long id) {
        log.debug("REST request to get SavingsProduct : {}", id);
        Optional<SavingsProductDTO> savingsProductDTO = savingsProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(savingsProductDTO);
    }

    /**
     * {@code DELETE  /savings-products/:id} : delete the "id" savingsProduct.
     *
     * @param id the id of the savingsProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/savings-products/{id}")
    public ResponseEntity<Void> deleteSavingsProduct(@PathVariable Long id) {
        log.debug("REST request to delete SavingsProduct : {}", id);
        savingsProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package com.tetra.bank.web.rest;

import com.tetra.bank.service.StaffService;
import com.tetra.bank.web.rest.errors.BadRequestAlertException;
import com.tetra.bank.service.dto.StaffDTO;

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
 * REST controller for managing {@link com.tetra.bank.domain.Staff}.
 */
@RestController
@RequestMapping("/api")
public class StaffResource {

    private final Logger log = LoggerFactory.getLogger(StaffResource.class);

    private static final String ENTITY_NAME = "staff";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StaffService staffService;

    public StaffResource(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * {@code POST  /staff} : Create a new staff.
     *
     * @param staffDTO the staffDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new staffDTO, or with status {@code 400 (Bad Request)} if the staff has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/staff")
    public ResponseEntity<StaffDTO> createStaff(@RequestBody StaffDTO staffDTO) throws URISyntaxException {
        log.debug("REST request to save Staff : {}", staffDTO);
        if (staffDTO.getId() != null) {
            throw new BadRequestAlertException("A new staff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaffDTO result = staffService.save(staffDTO);
        return ResponseEntity.created(new URI("/api/staff/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /staff} : Updates an existing staff.
     *
     * @param staffDTO the staffDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated staffDTO,
     * or with status {@code 400 (Bad Request)} if the staffDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the staffDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/staff")
    public ResponseEntity<StaffDTO> updateStaff(@RequestBody StaffDTO staffDTO) throws URISyntaxException {
        log.debug("REST request to update Staff : {}", staffDTO);
        if (staffDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StaffDTO result = staffService.save(staffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, staffDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /staff} : get all the staff.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of staff in body.
     */
    @GetMapping("/staff")
    public List<StaffDTO> getAllStaff() {
        log.debug("REST request to get all Staff");
        return staffService.findAll();
    }

    /**
     * {@code GET  /staff/:id} : get the "id" staff.
     *
     * @param id the id of the staffDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the staffDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/staff/{id}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable Long id) {
        log.debug("REST request to get Staff : {}", id);
        Optional<StaffDTO> staffDTO = staffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(staffDTO);
    }

    /**
     * {@code DELETE  /staff/:id} : delete the "id" staff.
     *
     * @param id the id of the staffDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        log.debug("REST request to delete Staff : {}", id);
        staffService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

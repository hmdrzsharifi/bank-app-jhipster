package com.tetra.bank.web.rest;

import com.tetra.bank.service.PaymentDetailsService;
import com.tetra.bank.web.rest.errors.BadRequestAlertException;
import com.tetra.bank.service.dto.PaymentDetailsDTO;

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
 * REST controller for managing {@link com.tetra.bank.domain.PaymentDetails}.
 */
@RestController
@RequestMapping("/api")
public class PaymentDetailsResource {

    private final Logger log = LoggerFactory.getLogger(PaymentDetailsResource.class);

    private static final String ENTITY_NAME = "paymentDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentDetailsService paymentDetailsService;

    public PaymentDetailsResource(PaymentDetailsService paymentDetailsService) {
        this.paymentDetailsService = paymentDetailsService;
    }

    /**
     * {@code POST  /payment-details} : Create a new paymentDetails.
     *
     * @param paymentDetailsDTO the paymentDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentDetailsDTO, or with status {@code 400 (Bad Request)} if the paymentDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-details")
    public ResponseEntity<PaymentDetailsDTO> createPaymentDetails(@RequestBody PaymentDetailsDTO paymentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save PaymentDetails : {}", paymentDetailsDTO);
        if (paymentDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentDetailsDTO result = paymentDetailsService.save(paymentDetailsDTO);
        return ResponseEntity.created(new URI("/api/payment-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-details} : Updates an existing paymentDetails.
     *
     * @param paymentDetailsDTO the paymentDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the paymentDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-details")
    public ResponseEntity<PaymentDetailsDTO> updatePaymentDetails(@RequestBody PaymentDetailsDTO paymentDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update PaymentDetails : {}", paymentDetailsDTO);
        if (paymentDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentDetailsDTO result = paymentDetailsService.save(paymentDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-details} : get all the paymentDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentDetails in body.
     */
    @GetMapping("/payment-details")
    public List<PaymentDetailsDTO> getAllPaymentDetails() {
        log.debug("REST request to get all PaymentDetails");
        return paymentDetailsService.findAll();
    }

    /**
     * {@code GET  /payment-details/:id} : get the "id" paymentDetails.
     *
     * @param id the id of the paymentDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-details/{id}")
    public ResponseEntity<PaymentDetailsDTO> getPaymentDetails(@PathVariable Long id) {
        log.debug("REST request to get PaymentDetails : {}", id);
        Optional<PaymentDetailsDTO> paymentDetailsDTO = paymentDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentDetailsDTO);
    }

    /**
     * {@code DELETE  /payment-details/:id} : delete the "id" paymentDetails.
     *
     * @param id the id of the paymentDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-details/{id}")
    public ResponseEntity<Void> deletePaymentDetails(@PathVariable Long id) {
        log.debug("REST request to delete PaymentDetails : {}", id);
        paymentDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

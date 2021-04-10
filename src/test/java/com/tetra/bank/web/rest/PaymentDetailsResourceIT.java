package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.PaymentDetails;
import com.tetra.bank.repository.PaymentDetailsRepository;
import com.tetra.bank.service.PaymentDetailsService;
import com.tetra.bank.service.dto.PaymentDetailsDTO;
import com.tetra.bank.service.mapper.PaymentDetailsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PaymentDetailsResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaymentDetailsResourceIT {

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private PaymentDetailsMapper paymentDetailsMapper;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentDetailsMockMvc;

    private PaymentDetails paymentDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentDetails createEntity(EntityManager em) {
        PaymentDetails paymentDetails = new PaymentDetails()
            .fieldName(DEFAULT_FIELD_NAME);
        return paymentDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentDetails createUpdatedEntity(EntityManager em) {
        PaymentDetails paymentDetails = new PaymentDetails()
            .fieldName(UPDATED_FIELD_NAME);
        return paymentDetails;
    }

    @BeforeEach
    public void initTest() {
        paymentDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentDetails() throws Exception {
        int databaseSizeBeforeCreate = paymentDetailsRepository.findAll().size();
        // Create the PaymentDetails
        PaymentDetailsDTO paymentDetailsDTO = paymentDetailsMapper.toDto(paymentDetails);
        restPaymentDetailsMockMvc.perform(post("/api/payment-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentDetails in the database
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        assertThat(paymentDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentDetails testPaymentDetails = paymentDetailsList.get(paymentDetailsList.size() - 1);
        assertThat(testPaymentDetails.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
    }

    @Test
    @Transactional
    public void createPaymentDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentDetailsRepository.findAll().size();

        // Create the PaymentDetails with an existing ID
        paymentDetails.setId(1L);
        PaymentDetailsDTO paymentDetailsDTO = paymentDetailsMapper.toDto(paymentDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentDetailsMockMvc.perform(post("/api/payment-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentDetails in the database
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        assertThat(paymentDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymentDetails() throws Exception {
        // Initialize the database
        paymentDetailsRepository.saveAndFlush(paymentDetails);

        // Get all the paymentDetailsList
        restPaymentDetailsMockMvc.perform(get("/api/payment-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME)));
    }
    
    @Test
    @Transactional
    public void getPaymentDetails() throws Exception {
        // Initialize the database
        paymentDetailsRepository.saveAndFlush(paymentDetails);

        // Get the paymentDetails
        restPaymentDetailsMockMvc.perform(get("/api/payment-details/{id}", paymentDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentDetails.getId().intValue()))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingPaymentDetails() throws Exception {
        // Get the paymentDetails
        restPaymentDetailsMockMvc.perform(get("/api/payment-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentDetails() throws Exception {
        // Initialize the database
        paymentDetailsRepository.saveAndFlush(paymentDetails);

        int databaseSizeBeforeUpdate = paymentDetailsRepository.findAll().size();

        // Update the paymentDetails
        PaymentDetails updatedPaymentDetails = paymentDetailsRepository.findById(paymentDetails.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentDetails are not directly saved in db
        em.detach(updatedPaymentDetails);
        updatedPaymentDetails
            .fieldName(UPDATED_FIELD_NAME);
        PaymentDetailsDTO paymentDetailsDTO = paymentDetailsMapper.toDto(updatedPaymentDetails);

        restPaymentDetailsMockMvc.perform(put("/api/payment-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentDetails in the database
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        assertThat(paymentDetailsList).hasSize(databaseSizeBeforeUpdate);
        PaymentDetails testPaymentDetails = paymentDetailsList.get(paymentDetailsList.size() - 1);
        assertThat(testPaymentDetails.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentDetails() throws Exception {
        int databaseSizeBeforeUpdate = paymentDetailsRepository.findAll().size();

        // Create the PaymentDetails
        PaymentDetailsDTO paymentDetailsDTO = paymentDetailsMapper.toDto(paymentDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentDetailsMockMvc.perform(put("/api/payment-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentDetails in the database
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        assertThat(paymentDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentDetails() throws Exception {
        // Initialize the database
        paymentDetailsRepository.saveAndFlush(paymentDetails);

        int databaseSizeBeforeDelete = paymentDetailsRepository.findAll().size();

        // Delete the paymentDetails
        restPaymentDetailsMockMvc.perform(delete("/api/payment-details/{id}", paymentDetails.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        assertThat(paymentDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

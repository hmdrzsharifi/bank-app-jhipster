package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.Client;
import com.tetra.bank.repository.ClientRepository;
import com.tetra.bank.service.ClientService;
import com.tetra.bank.service.dto.ClientDTO;
import com.tetra.bank.service.mapper.ClientMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ClientResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_ACTIVATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLENAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULLNAME = "AAAAAAAAAA";
    private static final String UPDATED_FULLNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_STAFF = false;
    private static final Boolean UPDATED_IS_STAFF = true;

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_OF_BIRTH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF_BIRTH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLOSURE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSURE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REJECTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REJECTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REACTIVATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REACTIVATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBMITTED_ON_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBMITTED_ON_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .status(DEFAULT_STATUS)
            .activationDate(DEFAULT_ACTIVATION_DATE)
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .middlename(DEFAULT_MIDDLENAME)
            .fullname(DEFAULT_FULLNAME)
            .displayName(DEFAULT_DISPLAY_NAME)
            .mobileNo(DEFAULT_MOBILE_NO)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .isStaff(DEFAULT_IS_STAFF)
            .externalId(DEFAULT_EXTERNAL_ID)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .closureDate(DEFAULT_CLOSURE_DATE)
            .rejectionDate(DEFAULT_REJECTION_DATE)
            .reactivateDate(DEFAULT_REACTIVATE_DATE)
            .submittedOnDate(DEFAULT_SUBMITTED_ON_DATE);
        return client;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity(EntityManager em) {
        Client client = new Client()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .middlename(UPDATED_MIDDLENAME)
            .fullname(UPDATED_FULLNAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .isStaff(UPDATED_IS_STAFF)
            .externalId(UPDATED_EXTERNAL_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .closureDate(UPDATED_CLOSURE_DATE)
            .rejectionDate(UPDATED_REJECTION_DATE)
            .reactivateDate(UPDATED_REACTIVATE_DATE)
            .submittedOnDate(UPDATED_SUBMITTED_ON_DATE);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();
        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc.perform(post("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testClient.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testClient.getActivationDate()).isEqualTo(DEFAULT_ACTIVATION_DATE);
        assertThat(testClient.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testClient.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testClient.getMiddlename()).isEqualTo(DEFAULT_MIDDLENAME);
        assertThat(testClient.getFullname()).isEqualTo(DEFAULT_FULLNAME);
        assertThat(testClient.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testClient.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testClient.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testClient.isIsStaff()).isEqualTo(DEFAULT_IS_STAFF);
        assertThat(testClient.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testClient.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testClient.getClosureDate()).isEqualTo(DEFAULT_CLOSURE_DATE);
        assertThat(testClient.getRejectionDate()).isEqualTo(DEFAULT_REJECTION_DATE);
        assertThat(testClient.getReactivateDate()).isEqualTo(DEFAULT_REACTIVATE_DATE);
        assertThat(testClient.getSubmittedOnDate()).isEqualTo(DEFAULT_SUBMITTED_ON_DATE);
    }

    @Test
    @Transactional
    public void createClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc.perform(post("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setAccountNumber(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);


        restClientMockMvc.perform(post("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsStaffIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientRepository.findAll().size();
        // set the field null
        client.setIsStaff(null);

        // Create the Client, which fails.
        ClientDTO clientDTO = clientMapper.toDto(client);


        restClientMockMvc.perform(post("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].activationDate").value(hasItem(DEFAULT_ACTIVATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].middlename").value(hasItem(DEFAULT_MIDDLENAME)))
            .andExpect(jsonPath("$.[*].fullname").value(hasItem(DEFAULT_FULLNAME)))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].isStaff").value(hasItem(DEFAULT_IS_STAFF.booleanValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].closureDate").value(hasItem(DEFAULT_CLOSURE_DATE.toString())))
            .andExpect(jsonPath("$.[*].rejectionDate").value(hasItem(DEFAULT_REJECTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].reactivateDate").value(hasItem(DEFAULT_REACTIVATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].submittedOnDate").value(hasItem(DEFAULT_SUBMITTED_ON_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.activationDate").value(DEFAULT_ACTIVATION_DATE.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.middlename").value(DEFAULT_MIDDLENAME))
            .andExpect(jsonPath("$.fullname").value(DEFAULT_FULLNAME))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.isStaff").value(DEFAULT_IS_STAFF.booleanValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.closureDate").value(DEFAULT_CLOSURE_DATE.toString()))
            .andExpect(jsonPath("$.rejectionDate").value(DEFAULT_REJECTION_DATE.toString()))
            .andExpect(jsonPath("$.reactivateDate").value(DEFAULT_REACTIVATE_DATE.toString()))
            .andExpect(jsonPath("$.submittedOnDate").value(DEFAULT_SUBMITTED_ON_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .status(UPDATED_STATUS)
            .activationDate(UPDATED_ACTIVATION_DATE)
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .middlename(UPDATED_MIDDLENAME)
            .fullname(UPDATED_FULLNAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .mobileNo(UPDATED_MOBILE_NO)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .isStaff(UPDATED_IS_STAFF)
            .externalId(UPDATED_EXTERNAL_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .closureDate(UPDATED_CLOSURE_DATE)
            .rejectionDate(UPDATED_REJECTION_DATE)
            .reactivateDate(UPDATED_REACTIVATE_DATE)
            .submittedOnDate(UPDATED_SUBMITTED_ON_DATE);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc.perform(put("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testClient.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testClient.getActivationDate()).isEqualTo(UPDATED_ACTIVATION_DATE);
        assertThat(testClient.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testClient.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testClient.getMiddlename()).isEqualTo(UPDATED_MIDDLENAME);
        assertThat(testClient.getFullname()).isEqualTo(UPDATED_FULLNAME);
        assertThat(testClient.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testClient.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testClient.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testClient.isIsStaff()).isEqualTo(UPDATED_IS_STAFF);
        assertThat(testClient.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testClient.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testClient.getClosureDate()).isEqualTo(UPDATED_CLOSURE_DATE);
        assertThat(testClient.getRejectionDate()).isEqualTo(UPDATED_REJECTION_DATE);
        assertThat(testClient.getReactivateDate()).isEqualTo(UPDATED_REACTIVATE_DATE);
        assertThat(testClient.getSubmittedOnDate()).isEqualTo(UPDATED_SUBMITTED_ON_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc.perform(put("/api/clients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Delete the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

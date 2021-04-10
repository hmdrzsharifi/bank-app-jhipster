package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.ClientNonPerson;
import com.tetra.bank.repository.ClientNonPersonRepository;
import com.tetra.bank.service.ClientNonPersonService;
import com.tetra.bank.service.dto.ClientNonPersonDTO;
import com.tetra.bank.service.mapper.ClientNonPersonMapper;

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
 * Integration tests for the {@link ClientNonPersonResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientNonPersonResourceIT {

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    @Autowired
    private ClientNonPersonRepository clientNonPersonRepository;

    @Autowired
    private ClientNonPersonMapper clientNonPersonMapper;

    @Autowired
    private ClientNonPersonService clientNonPersonService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientNonPersonMockMvc;

    private ClientNonPerson clientNonPerson;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientNonPerson createEntity(EntityManager em) {
        ClientNonPerson clientNonPerson = new ClientNonPerson()
            .fieldName(DEFAULT_FIELD_NAME);
        return clientNonPerson;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientNonPerson createUpdatedEntity(EntityManager em) {
        ClientNonPerson clientNonPerson = new ClientNonPerson()
            .fieldName(UPDATED_FIELD_NAME);
        return clientNonPerson;
    }

    @BeforeEach
    public void initTest() {
        clientNonPerson = createEntity(em);
    }

    @Test
    @Transactional
    public void createClientNonPerson() throws Exception {
        int databaseSizeBeforeCreate = clientNonPersonRepository.findAll().size();
        // Create the ClientNonPerson
        ClientNonPersonDTO clientNonPersonDTO = clientNonPersonMapper.toDto(clientNonPerson);
        restClientNonPersonMockMvc.perform(post("/api/client-non-people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientNonPersonDTO)))
            .andExpect(status().isCreated());

        // Validate the ClientNonPerson in the database
        List<ClientNonPerson> clientNonPersonList = clientNonPersonRepository.findAll();
        assertThat(clientNonPersonList).hasSize(databaseSizeBeforeCreate + 1);
        ClientNonPerson testClientNonPerson = clientNonPersonList.get(clientNonPersonList.size() - 1);
        assertThat(testClientNonPerson.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
    }

    @Test
    @Transactional
    public void createClientNonPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientNonPersonRepository.findAll().size();

        // Create the ClientNonPerson with an existing ID
        clientNonPerson.setId(1L);
        ClientNonPersonDTO clientNonPersonDTO = clientNonPersonMapper.toDto(clientNonPerson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientNonPersonMockMvc.perform(post("/api/client-non-people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientNonPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientNonPerson in the database
        List<ClientNonPerson> clientNonPersonList = clientNonPersonRepository.findAll();
        assertThat(clientNonPersonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClientNonPeople() throws Exception {
        // Initialize the database
        clientNonPersonRepository.saveAndFlush(clientNonPerson);

        // Get all the clientNonPersonList
        restClientNonPersonMockMvc.perform(get("/api/client-non-people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientNonPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME)));
    }
    
    @Test
    @Transactional
    public void getClientNonPerson() throws Exception {
        // Initialize the database
        clientNonPersonRepository.saveAndFlush(clientNonPerson);

        // Get the clientNonPerson
        restClientNonPersonMockMvc.perform(get("/api/client-non-people/{id}", clientNonPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientNonPerson.getId().intValue()))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingClientNonPerson() throws Exception {
        // Get the clientNonPerson
        restClientNonPersonMockMvc.perform(get("/api/client-non-people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClientNonPerson() throws Exception {
        // Initialize the database
        clientNonPersonRepository.saveAndFlush(clientNonPerson);

        int databaseSizeBeforeUpdate = clientNonPersonRepository.findAll().size();

        // Update the clientNonPerson
        ClientNonPerson updatedClientNonPerson = clientNonPersonRepository.findById(clientNonPerson.getId()).get();
        // Disconnect from session so that the updates on updatedClientNonPerson are not directly saved in db
        em.detach(updatedClientNonPerson);
        updatedClientNonPerson
            .fieldName(UPDATED_FIELD_NAME);
        ClientNonPersonDTO clientNonPersonDTO = clientNonPersonMapper.toDto(updatedClientNonPerson);

        restClientNonPersonMockMvc.perform(put("/api/client-non-people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientNonPersonDTO)))
            .andExpect(status().isOk());

        // Validate the ClientNonPerson in the database
        List<ClientNonPerson> clientNonPersonList = clientNonPersonRepository.findAll();
        assertThat(clientNonPersonList).hasSize(databaseSizeBeforeUpdate);
        ClientNonPerson testClientNonPerson = clientNonPersonList.get(clientNonPersonList.size() - 1);
        assertThat(testClientNonPerson.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingClientNonPerson() throws Exception {
        int databaseSizeBeforeUpdate = clientNonPersonRepository.findAll().size();

        // Create the ClientNonPerson
        ClientNonPersonDTO clientNonPersonDTO = clientNonPersonMapper.toDto(clientNonPerson);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientNonPersonMockMvc.perform(put("/api/client-non-people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientNonPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ClientNonPerson in the database
        List<ClientNonPerson> clientNonPersonList = clientNonPersonRepository.findAll();
        assertThat(clientNonPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClientNonPerson() throws Exception {
        // Initialize the database
        clientNonPersonRepository.saveAndFlush(clientNonPerson);

        int databaseSizeBeforeDelete = clientNonPersonRepository.findAll().size();

        // Delete the clientNonPerson
        restClientNonPersonMockMvc.perform(delete("/api/client-non-people/{id}", clientNonPerson.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientNonPerson> clientNonPersonList = clientNonPersonRepository.findAll();
        assertThat(clientNonPersonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.CodeValue;
import com.tetra.bank.repository.CodeValueRepository;
import com.tetra.bank.service.CodeValueService;
import com.tetra.bank.service.dto.CodeValueDTO;
import com.tetra.bank.service.mapper.CodeValueMapper;

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
 * Integration tests for the {@link CodeValueResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CodeValueResourceIT {

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    @Autowired
    private CodeValueRepository codeValueRepository;

    @Autowired
    private CodeValueMapper codeValueMapper;

    @Autowired
    private CodeValueService codeValueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCodeValueMockMvc;

    private CodeValue codeValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeValue createEntity(EntityManager em) {
        CodeValue codeValue = new CodeValue()
            .fieldName(DEFAULT_FIELD_NAME);
        return codeValue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeValue createUpdatedEntity(EntityManager em) {
        CodeValue codeValue = new CodeValue()
            .fieldName(UPDATED_FIELD_NAME);
        return codeValue;
    }

    @BeforeEach
    public void initTest() {
        codeValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodeValue() throws Exception {
        int databaseSizeBeforeCreate = codeValueRepository.findAll().size();
        // Create the CodeValue
        CodeValueDTO codeValueDTO = codeValueMapper.toDto(codeValue);
        restCodeValueMockMvc.perform(post("/api/code-values").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeValueDTO)))
            .andExpect(status().isCreated());

        // Validate the CodeValue in the database
        List<CodeValue> codeValueList = codeValueRepository.findAll();
        assertThat(codeValueList).hasSize(databaseSizeBeforeCreate + 1);
        CodeValue testCodeValue = codeValueList.get(codeValueList.size() - 1);
        assertThat(testCodeValue.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
    }

    @Test
    @Transactional
    public void createCodeValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codeValueRepository.findAll().size();

        // Create the CodeValue with an existing ID
        codeValue.setId(1L);
        CodeValueDTO codeValueDTO = codeValueMapper.toDto(codeValue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodeValueMockMvc.perform(post("/api/code-values").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodeValue in the database
        List<CodeValue> codeValueList = codeValueRepository.findAll();
        assertThat(codeValueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCodeValues() throws Exception {
        // Initialize the database
        codeValueRepository.saveAndFlush(codeValue);

        // Get all the codeValueList
        restCodeValueMockMvc.perform(get("/api/code-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codeValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME)));
    }
    
    @Test
    @Transactional
    public void getCodeValue() throws Exception {
        // Initialize the database
        codeValueRepository.saveAndFlush(codeValue);

        // Get the codeValue
        restCodeValueMockMvc.perform(get("/api/code-values/{id}", codeValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(codeValue.getId().intValue()))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCodeValue() throws Exception {
        // Get the codeValue
        restCodeValueMockMvc.perform(get("/api/code-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodeValue() throws Exception {
        // Initialize the database
        codeValueRepository.saveAndFlush(codeValue);

        int databaseSizeBeforeUpdate = codeValueRepository.findAll().size();

        // Update the codeValue
        CodeValue updatedCodeValue = codeValueRepository.findById(codeValue.getId()).get();
        // Disconnect from session so that the updates on updatedCodeValue are not directly saved in db
        em.detach(updatedCodeValue);
        updatedCodeValue
            .fieldName(UPDATED_FIELD_NAME);
        CodeValueDTO codeValueDTO = codeValueMapper.toDto(updatedCodeValue);

        restCodeValueMockMvc.perform(put("/api/code-values").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeValueDTO)))
            .andExpect(status().isOk());

        // Validate the CodeValue in the database
        List<CodeValue> codeValueList = codeValueRepository.findAll();
        assertThat(codeValueList).hasSize(databaseSizeBeforeUpdate);
        CodeValue testCodeValue = codeValueList.get(codeValueList.size() - 1);
        assertThat(testCodeValue.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCodeValue() throws Exception {
        int databaseSizeBeforeUpdate = codeValueRepository.findAll().size();

        // Create the CodeValue
        CodeValueDTO codeValueDTO = codeValueMapper.toDto(codeValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCodeValueMockMvc.perform(put("/api/code-values").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CodeValue in the database
        List<CodeValue> codeValueList = codeValueRepository.findAll();
        assertThat(codeValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodeValue() throws Exception {
        // Initialize the database
        codeValueRepository.saveAndFlush(codeValue);

        int databaseSizeBeforeDelete = codeValueRepository.findAll().size();

        // Delete the codeValue
        restCodeValueMockMvc.perform(delete("/api/code-values/{id}", codeValue.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CodeValue> codeValueList = codeValueRepository.findAll();
        assertThat(codeValueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

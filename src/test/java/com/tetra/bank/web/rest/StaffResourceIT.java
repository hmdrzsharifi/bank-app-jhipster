package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.Staff;
import com.tetra.bank.repository.StaffRepository;
import com.tetra.bank.service.StaffService;
import com.tetra.bank.service.dto.StaffDTO;
import com.tetra.bank.service.mapper.StaffMapper;

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
 * Integration tests for the {@link StaffResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StaffResourceIT {

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private StaffService staffService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStaffMockMvc;

    private Staff staff;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Staff createEntity(EntityManager em) {
        Staff staff = new Staff()
            .fieldName(DEFAULT_FIELD_NAME);
        return staff;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Staff createUpdatedEntity(EntityManager em) {
        Staff staff = new Staff()
            .fieldName(UPDATED_FIELD_NAME);
        return staff;
    }

    @BeforeEach
    public void initTest() {
        staff = createEntity(em);
    }

    @Test
    @Transactional
    public void createStaff() throws Exception {
        int databaseSizeBeforeCreate = staffRepository.findAll().size();
        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);
        restStaffMockMvc.perform(post("/api/staff").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffDTO)))
            .andExpect(status().isCreated());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeCreate + 1);
        Staff testStaff = staffList.get(staffList.size() - 1);
        assertThat(testStaff.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
    }

    @Test
    @Transactional
    public void createStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = staffRepository.findAll().size();

        // Create the Staff with an existing ID
        staff.setId(1L);
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffMockMvc.perform(post("/api/staff").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        // Get all the staffList
        restStaffMockMvc.perform(get("/api/staff?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staff.getId().intValue())))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME)));
    }
    
    @Test
    @Transactional
    public void getStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        // Get the staff
        restStaffMockMvc.perform(get("/api/staff/{id}", staff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(staff.getId().intValue()))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingStaff() throws Exception {
        // Get the staff
        restStaffMockMvc.perform(get("/api/staff/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        int databaseSizeBeforeUpdate = staffRepository.findAll().size();

        // Update the staff
        Staff updatedStaff = staffRepository.findById(staff.getId()).get();
        // Disconnect from session so that the updates on updatedStaff are not directly saved in db
        em.detach(updatedStaff);
        updatedStaff
            .fieldName(UPDATED_FIELD_NAME);
        StaffDTO staffDTO = staffMapper.toDto(updatedStaff);

        restStaffMockMvc.perform(put("/api/staff").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffDTO)))
            .andExpect(status().isOk());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeUpdate);
        Staff testStaff = staffList.get(staffList.size() - 1);
        assertThat(testStaff.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingStaff() throws Exception {
        int databaseSizeBeforeUpdate = staffRepository.findAll().size();

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffMockMvc.perform(put("/api/staff").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(staffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStaff() throws Exception {
        // Initialize the database
        staffRepository.saveAndFlush(staff);

        int databaseSizeBeforeDelete = staffRepository.findAll().size();

        // Delete the staff
        restStaffMockMvc.perform(delete("/api/staff/{id}", staff.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Staff> staffList = staffRepository.findAll();
        assertThat(staffList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

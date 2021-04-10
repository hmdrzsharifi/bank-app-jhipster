package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.SavingsAccountTransaction;
import com.tetra.bank.repository.SavingsAccountTransactionRepository;
import com.tetra.bank.service.SavingsAccountTransactionService;
import com.tetra.bank.service.dto.SavingsAccountTransactionDTO;
import com.tetra.bank.service.mapper.SavingsAccountTransactionMapper;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SavingsAccountTransactionResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SavingsAccountTransactionResourceIT {

    private static final Integer DEFAULT_TRANSACTION_TYPE = 1;
    private static final Integer UPDATED_TRANSACTION_TYPE = 2;

    private static final Boolean DEFAULT_REVERSED = false;
    private static final Boolean UPDATED_REVERSED = true;

    private static final Instant DEFAULT_DATE_OF = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_OF = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OVERDRAFT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDRAFT_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_BALANCE_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BALANCE_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_BALANCE_NUMBER_OF_DAYS = 1;
    private static final Integer UPDATED_BALANCE_NUMBER_OF_DAYS = 2;

    private static final BigDecimal DEFAULT_RUNNING_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RUNNING_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CUMULATIVE_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CUMULATIVE_BALANCE = new BigDecimal(2);

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_MANUAL_TRANSACTION = false;
    private static final Boolean UPDATED_IS_MANUAL_TRANSACTION = true;

    private static final Long DEFAULT_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION = 1L;
    private static final Long UPDATED_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION = 2L;

    @Autowired
    private SavingsAccountTransactionRepository savingsAccountTransactionRepository;

    @Autowired
    private SavingsAccountTransactionMapper savingsAccountTransactionMapper;

    @Autowired
    private SavingsAccountTransactionService savingsAccountTransactionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSavingsAccountTransactionMockMvc;

    private SavingsAccountTransaction savingsAccountTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsAccountTransaction createEntity(EntityManager em) {
        SavingsAccountTransaction savingsAccountTransaction = new SavingsAccountTransaction()
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .reversed(DEFAULT_REVERSED)
            .dateOf(DEFAULT_DATE_OF)
            .amount(DEFAULT_AMOUNT)
            .overdraftAmount(DEFAULT_OVERDRAFT_AMOUNT)
            .balanceEndDate(DEFAULT_BALANCE_END_DATE)
            .balanceNumberOfDays(DEFAULT_BALANCE_NUMBER_OF_DAYS)
            .runningBalance(DEFAULT_RUNNING_BALANCE)
            .cumulativeBalance(DEFAULT_CUMULATIVE_BALANCE)
            .createdDate(DEFAULT_CREATED_DATE)
            .isManualTransaction(DEFAULT_IS_MANUAL_TRANSACTION)
            .releaseIdOfHoldAmountTransaction(DEFAULT_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION);
        return savingsAccountTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsAccountTransaction createUpdatedEntity(EntityManager em) {
        SavingsAccountTransaction savingsAccountTransaction = new SavingsAccountTransaction()
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .reversed(UPDATED_REVERSED)
            .dateOf(UPDATED_DATE_OF)
            .amount(UPDATED_AMOUNT)
            .overdraftAmount(UPDATED_OVERDRAFT_AMOUNT)
            .balanceEndDate(UPDATED_BALANCE_END_DATE)
            .balanceNumberOfDays(UPDATED_BALANCE_NUMBER_OF_DAYS)
            .runningBalance(UPDATED_RUNNING_BALANCE)
            .cumulativeBalance(UPDATED_CUMULATIVE_BALANCE)
            .createdDate(UPDATED_CREATED_DATE)
            .isManualTransaction(UPDATED_IS_MANUAL_TRANSACTION)
            .releaseIdOfHoldAmountTransaction(UPDATED_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION);
        return savingsAccountTransaction;
    }

    @BeforeEach
    public void initTest() {
        savingsAccountTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createSavingsAccountTransaction() throws Exception {
        int databaseSizeBeforeCreate = savingsAccountTransactionRepository.findAll().size();
        // Create the SavingsAccountTransaction
        SavingsAccountTransactionDTO savingsAccountTransactionDTO = savingsAccountTransactionMapper.toDto(savingsAccountTransaction);
        restSavingsAccountTransactionMockMvc.perform(post("/api/savings-account-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the SavingsAccountTransaction in the database
        List<SavingsAccountTransaction> savingsAccountTransactionList = savingsAccountTransactionRepository.findAll();
        assertThat(savingsAccountTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        SavingsAccountTransaction testSavingsAccountTransaction = savingsAccountTransactionList.get(savingsAccountTransactionList.size() - 1);
        assertThat(testSavingsAccountTransaction.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testSavingsAccountTransaction.isReversed()).isEqualTo(DEFAULT_REVERSED);
        assertThat(testSavingsAccountTransaction.getDateOf()).isEqualTo(DEFAULT_DATE_OF);
        assertThat(testSavingsAccountTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSavingsAccountTransaction.getOverdraftAmount()).isEqualTo(DEFAULT_OVERDRAFT_AMOUNT);
        assertThat(testSavingsAccountTransaction.getBalanceEndDate()).isEqualTo(DEFAULT_BALANCE_END_DATE);
        assertThat(testSavingsAccountTransaction.getBalanceNumberOfDays()).isEqualTo(DEFAULT_BALANCE_NUMBER_OF_DAYS);
        assertThat(testSavingsAccountTransaction.getRunningBalance()).isEqualTo(DEFAULT_RUNNING_BALANCE);
        assertThat(testSavingsAccountTransaction.getCumulativeBalance()).isEqualTo(DEFAULT_CUMULATIVE_BALANCE);
        assertThat(testSavingsAccountTransaction.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSavingsAccountTransaction.isIsManualTransaction()).isEqualTo(DEFAULT_IS_MANUAL_TRANSACTION);
        assertThat(testSavingsAccountTransaction.getReleaseIdOfHoldAmountTransaction()).isEqualTo(DEFAULT_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION);
    }

    @Test
    @Transactional
    public void createSavingsAccountTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = savingsAccountTransactionRepository.findAll().size();

        // Create the SavingsAccountTransaction with an existing ID
        savingsAccountTransaction.setId(1L);
        SavingsAccountTransactionDTO savingsAccountTransactionDTO = savingsAccountTransactionMapper.toDto(savingsAccountTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSavingsAccountTransactionMockMvc.perform(post("/api/savings-account-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsAccountTransaction in the database
        List<SavingsAccountTransaction> savingsAccountTransactionList = savingsAccountTransactionRepository.findAll();
        assertThat(savingsAccountTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSavingsAccountTransactions() throws Exception {
        // Initialize the database
        savingsAccountTransactionRepository.saveAndFlush(savingsAccountTransaction);

        // Get all the savingsAccountTransactionList
        restSavingsAccountTransactionMockMvc.perform(get("/api/savings-account-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(savingsAccountTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)))
            .andExpect(jsonPath("$.[*].reversed").value(hasItem(DEFAULT_REVERSED.booleanValue())))
            .andExpect(jsonPath("$.[*].dateOf").value(hasItem(DEFAULT_DATE_OF.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].overdraftAmount").value(hasItem(DEFAULT_OVERDRAFT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].balanceEndDate").value(hasItem(DEFAULT_BALANCE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].balanceNumberOfDays").value(hasItem(DEFAULT_BALANCE_NUMBER_OF_DAYS)))
            .andExpect(jsonPath("$.[*].runningBalance").value(hasItem(DEFAULT_RUNNING_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].cumulativeBalance").value(hasItem(DEFAULT_CUMULATIVE_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].isManualTransaction").value(hasItem(DEFAULT_IS_MANUAL_TRANSACTION.booleanValue())))
            .andExpect(jsonPath("$.[*].releaseIdOfHoldAmountTransaction").value(hasItem(DEFAULT_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION.intValue())));
    }
    
    @Test
    @Transactional
    public void getSavingsAccountTransaction() throws Exception {
        // Initialize the database
        savingsAccountTransactionRepository.saveAndFlush(savingsAccountTransaction);

        // Get the savingsAccountTransaction
        restSavingsAccountTransactionMockMvc.perform(get("/api/savings-account-transactions/{id}", savingsAccountTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(savingsAccountTransaction.getId().intValue()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE))
            .andExpect(jsonPath("$.reversed").value(DEFAULT_REVERSED.booleanValue()))
            .andExpect(jsonPath("$.dateOf").value(DEFAULT_DATE_OF.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.overdraftAmount").value(DEFAULT_OVERDRAFT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.balanceEndDate").value(DEFAULT_BALANCE_END_DATE.toString()))
            .andExpect(jsonPath("$.balanceNumberOfDays").value(DEFAULT_BALANCE_NUMBER_OF_DAYS))
            .andExpect(jsonPath("$.runningBalance").value(DEFAULT_RUNNING_BALANCE.intValue()))
            .andExpect(jsonPath("$.cumulativeBalance").value(DEFAULT_CUMULATIVE_BALANCE.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.isManualTransaction").value(DEFAULT_IS_MANUAL_TRANSACTION.booleanValue()))
            .andExpect(jsonPath("$.releaseIdOfHoldAmountTransaction").value(DEFAULT_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSavingsAccountTransaction() throws Exception {
        // Get the savingsAccountTransaction
        restSavingsAccountTransactionMockMvc.perform(get("/api/savings-account-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSavingsAccountTransaction() throws Exception {
        // Initialize the database
        savingsAccountTransactionRepository.saveAndFlush(savingsAccountTransaction);

        int databaseSizeBeforeUpdate = savingsAccountTransactionRepository.findAll().size();

        // Update the savingsAccountTransaction
        SavingsAccountTransaction updatedSavingsAccountTransaction = savingsAccountTransactionRepository.findById(savingsAccountTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedSavingsAccountTransaction are not directly saved in db
        em.detach(updatedSavingsAccountTransaction);
        updatedSavingsAccountTransaction
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .reversed(UPDATED_REVERSED)
            .dateOf(UPDATED_DATE_OF)
            .amount(UPDATED_AMOUNT)
            .overdraftAmount(UPDATED_OVERDRAFT_AMOUNT)
            .balanceEndDate(UPDATED_BALANCE_END_DATE)
            .balanceNumberOfDays(UPDATED_BALANCE_NUMBER_OF_DAYS)
            .runningBalance(UPDATED_RUNNING_BALANCE)
            .cumulativeBalance(UPDATED_CUMULATIVE_BALANCE)
            .createdDate(UPDATED_CREATED_DATE)
            .isManualTransaction(UPDATED_IS_MANUAL_TRANSACTION)
            .releaseIdOfHoldAmountTransaction(UPDATED_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION);
        SavingsAccountTransactionDTO savingsAccountTransactionDTO = savingsAccountTransactionMapper.toDto(updatedSavingsAccountTransaction);

        restSavingsAccountTransactionMockMvc.perform(put("/api/savings-account-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the SavingsAccountTransaction in the database
        List<SavingsAccountTransaction> savingsAccountTransactionList = savingsAccountTransactionRepository.findAll();
        assertThat(savingsAccountTransactionList).hasSize(databaseSizeBeforeUpdate);
        SavingsAccountTransaction testSavingsAccountTransaction = savingsAccountTransactionList.get(savingsAccountTransactionList.size() - 1);
        assertThat(testSavingsAccountTransaction.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testSavingsAccountTransaction.isReversed()).isEqualTo(UPDATED_REVERSED);
        assertThat(testSavingsAccountTransaction.getDateOf()).isEqualTo(UPDATED_DATE_OF);
        assertThat(testSavingsAccountTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSavingsAccountTransaction.getOverdraftAmount()).isEqualTo(UPDATED_OVERDRAFT_AMOUNT);
        assertThat(testSavingsAccountTransaction.getBalanceEndDate()).isEqualTo(UPDATED_BALANCE_END_DATE);
        assertThat(testSavingsAccountTransaction.getBalanceNumberOfDays()).isEqualTo(UPDATED_BALANCE_NUMBER_OF_DAYS);
        assertThat(testSavingsAccountTransaction.getRunningBalance()).isEqualTo(UPDATED_RUNNING_BALANCE);
        assertThat(testSavingsAccountTransaction.getCumulativeBalance()).isEqualTo(UPDATED_CUMULATIVE_BALANCE);
        assertThat(testSavingsAccountTransaction.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSavingsAccountTransaction.isIsManualTransaction()).isEqualTo(UPDATED_IS_MANUAL_TRANSACTION);
        assertThat(testSavingsAccountTransaction.getReleaseIdOfHoldAmountTransaction()).isEqualTo(UPDATED_RELEASE_ID_OF_HOLD_AMOUNT_TRANSACTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSavingsAccountTransaction() throws Exception {
        int databaseSizeBeforeUpdate = savingsAccountTransactionRepository.findAll().size();

        // Create the SavingsAccountTransaction
        SavingsAccountTransactionDTO savingsAccountTransactionDTO = savingsAccountTransactionMapper.toDto(savingsAccountTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSavingsAccountTransactionMockMvc.perform(put("/api/savings-account-transactions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsAccountTransaction in the database
        List<SavingsAccountTransaction> savingsAccountTransactionList = savingsAccountTransactionRepository.findAll();
        assertThat(savingsAccountTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSavingsAccountTransaction() throws Exception {
        // Initialize the database
        savingsAccountTransactionRepository.saveAndFlush(savingsAccountTransaction);

        int databaseSizeBeforeDelete = savingsAccountTransactionRepository.findAll().size();

        // Delete the savingsAccountTransaction
        restSavingsAccountTransactionMockMvc.perform(delete("/api/savings-account-transactions/{id}", savingsAccountTransaction.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SavingsAccountTransaction> savingsAccountTransactionList = savingsAccountTransactionRepository.findAll();
        assertThat(savingsAccountTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.SavingsAccount;
import com.tetra.bank.repository.SavingsAccountRepository;
import com.tetra.bank.service.SavingsAccountService;
import com.tetra.bank.service.dto.SavingsAccountDTO;
import com.tetra.bank.service.mapper.SavingsAccountMapper;

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
 * Integration tests for the {@link SavingsAccountResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SavingsAccountResourceIT {

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_SUB_STATUS = 1;
    private static final Integer UPDATED_SUB_STATUS = 2;

    private static final Integer DEFAULT_ACCOUNT_TYPE = 1;
    private static final Integer UPDATED_ACCOUNT_TYPE = 2;

    private static final Instant DEFAULT_SUBMITTED_ON_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBMITTED_ON_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_REJECTED_ON_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REJECTED_ON_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_APPROVED_ON_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPROVED_ON_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOMINAL_ANNUAL_INTEREST_RATE = new BigDecimal(2);

    private static final Integer DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE = 1;
    private static final Integer UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE = 2;

    private static final Integer DEFAULT_INTEREST_POSTING_PERIOD_TYPE = 1;
    private static final Integer UPDATED_INTEREST_POSTING_PERIOD_TYPE = 2;

    private static final Integer DEFAULT_INTEREST_CALCULATION_TYPE = 1;
    private static final Integer UPDATED_INTEREST_CALCULATION_TYPE = 2;

    private static final Integer DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE = 1;
    private static final Integer UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE = 2;

    private static final BigDecimal DEFAULT_MIN_REQUIRED_OPENING_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_REQUIRED_OPENING_BALANCE = new BigDecimal(2);

    private static final Integer DEFAULT_LOCKIN_PERIOD_FREQUENCY = 1;
    private static final Integer UPDATED_LOCKIN_PERIOD_FREQUENCY = 2;

    private static final Integer DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE = 1;
    private static final Integer UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE = 2;

    private static final BigDecimal DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION = new BigDecimal(2);

    private static final Boolean DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE = false;
    private static final Boolean UPDATED_ENFORCE_MIN_REQUIRED_BALANCE = true;

    private static final BigDecimal DEFAULT_MIN_REQUIRED_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_REQUIRED_BALANCE = new BigDecimal(2);

    private static final Boolean DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER = false;
    private static final Boolean UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER = true;

    private static final Boolean DEFAULT_ALLOW_OVERDRAFT = false;
    private static final Boolean UPDATED_ALLOW_OVERDRAFT = true;

    private static final BigDecimal DEFAULT_OVERDRAFT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDRAFT_LIMIT = new BigDecimal(2);

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private SavingsAccountMapper savingsAccountMapper;

    @Autowired
    private SavingsAccountService savingsAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSavingsAccountMockMvc;

    private SavingsAccount savingsAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsAccount createEntity(EntityManager em) {
        SavingsAccount savingsAccount = new SavingsAccount()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .externalId(DEFAULT_EXTERNAL_ID)
            .status(DEFAULT_STATUS)
            .subStatus(DEFAULT_SUB_STATUS)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .submittedOnDate(DEFAULT_SUBMITTED_ON_DATE)
            .rejectedOnDate(DEFAULT_REJECTED_ON_DATE)
            .approvedOnDate(DEFAULT_APPROVED_ON_DATE)
            .nominalAnnualInterestRate(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(DEFAULT_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(DEFAULT_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(DEFAULT_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(DEFAULT_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .nominalAnnualInterestRateOverdraft(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .minBalanceForInterestCalculation(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(DEFAULT_MIN_REQUIRED_BALANCE)
            .withdrawalFeeApplicableForTransfer(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(DEFAULT_ALLOW_OVERDRAFT)
            .overdraftLimit(DEFAULT_OVERDRAFT_LIMIT);
        return savingsAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsAccount createUpdatedEntity(EntityManager em) {
        SavingsAccount savingsAccount = new SavingsAccount()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .externalId(UPDATED_EXTERNAL_ID)
            .status(UPDATED_STATUS)
            .subStatus(UPDATED_SUB_STATUS)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .submittedOnDate(UPDATED_SUBMITTED_ON_DATE)
            .rejectedOnDate(UPDATED_REJECTED_ON_DATE)
            .approvedOnDate(UPDATED_APPROVED_ON_DATE)
            .nominalAnnualInterestRate(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(UPDATED_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(UPDATED_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(UPDATED_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(UPDATED_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .nominalAnnualInterestRateOverdraft(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .minBalanceForInterestCalculation(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(UPDATED_MIN_REQUIRED_BALANCE)
            .withdrawalFeeApplicableForTransfer(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(UPDATED_ALLOW_OVERDRAFT)
            .overdraftLimit(UPDATED_OVERDRAFT_LIMIT);
        return savingsAccount;
    }

    @BeforeEach
    public void initTest() {
        savingsAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createSavingsAccount() throws Exception {
        int databaseSizeBeforeCreate = savingsAccountRepository.findAll().size();
        // Create the SavingsAccount
        SavingsAccountDTO savingsAccountDTO = savingsAccountMapper.toDto(savingsAccount);
        restSavingsAccountMockMvc.perform(post("/api/savings-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the SavingsAccount in the database
        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeCreate + 1);
        SavingsAccount testSavingsAccount = savingsAccountList.get(savingsAccountList.size() - 1);
        assertThat(testSavingsAccount.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testSavingsAccount.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testSavingsAccount.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSavingsAccount.getSubStatus()).isEqualTo(DEFAULT_SUB_STATUS);
        assertThat(testSavingsAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testSavingsAccount.getSubmittedOnDate()).isEqualTo(DEFAULT_SUBMITTED_ON_DATE);
        assertThat(testSavingsAccount.getRejectedOnDate()).isEqualTo(DEFAULT_REJECTED_ON_DATE);
        assertThat(testSavingsAccount.getApprovedOnDate()).isEqualTo(DEFAULT_APPROVED_ON_DATE);
        assertThat(testSavingsAccount.getNominalAnnualInterestRate()).isEqualTo(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE);
        assertThat(testSavingsAccount.getInterestCompoundingPeriodType()).isEqualTo(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE);
        assertThat(testSavingsAccount.getInterestPostingPeriodType()).isEqualTo(DEFAULT_INTEREST_POSTING_PERIOD_TYPE);
        assertThat(testSavingsAccount.getInterestCalculationType()).isEqualTo(DEFAULT_INTEREST_CALCULATION_TYPE);
        assertThat(testSavingsAccount.getInterestCalculationDaysInYearType()).isEqualTo(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE);
        assertThat(testSavingsAccount.getMinRequiredOpeningBalance()).isEqualTo(DEFAULT_MIN_REQUIRED_OPENING_BALANCE);
        assertThat(testSavingsAccount.getLockinPeriodFrequency()).isEqualTo(DEFAULT_LOCKIN_PERIOD_FREQUENCY);
        assertThat(testSavingsAccount.getLockinPeriodFrequencyType()).isEqualTo(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE);
        assertThat(testSavingsAccount.getNominalAnnualInterestRateOverdraft()).isEqualTo(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT);
        assertThat(testSavingsAccount.getMinOverdraftForInterestCalculation()).isEqualTo(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsAccount.getMinBalanceForInterestCalculation()).isEqualTo(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsAccount.isEnforceMinRequiredBalance()).isEqualTo(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsAccount.getMinRequiredBalance()).isEqualTo(DEFAULT_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsAccount.isWithdrawalFeeApplicableForTransfer()).isEqualTo(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER);
        assertThat(testSavingsAccount.isAllowOverdraft()).isEqualTo(DEFAULT_ALLOW_OVERDRAFT);
        assertThat(testSavingsAccount.getOverdraftLimit()).isEqualTo(DEFAULT_OVERDRAFT_LIMIT);
    }

    @Test
    @Transactional
    public void createSavingsAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = savingsAccountRepository.findAll().size();

        // Create the SavingsAccount with an existing ID
        savingsAccount.setId(1L);
        SavingsAccountDTO savingsAccountDTO = savingsAccountMapper.toDto(savingsAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSavingsAccountMockMvc.perform(post("/api/savings-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsAccount in the database
        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = savingsAccountRepository.findAll().size();
        // set the field null
        savingsAccount.setAccountNumber(null);

        // Create the SavingsAccount, which fails.
        SavingsAccountDTO savingsAccountDTO = savingsAccountMapper.toDto(savingsAccount);


        restSavingsAccountMockMvc.perform(post("/api/savings-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountDTO)))
            .andExpect(status().isBadRequest());

        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSavingsAccounts() throws Exception {
        // Initialize the database
        savingsAccountRepository.saveAndFlush(savingsAccount);

        // Get all the savingsAccountList
        restSavingsAccountMockMvc.perform(get("/api/savings-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(savingsAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].subStatus").value(hasItem(DEFAULT_SUB_STATUS)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].submittedOnDate").value(hasItem(DEFAULT_SUBMITTED_ON_DATE.toString())))
            .andExpect(jsonPath("$.[*].rejectedOnDate").value(hasItem(DEFAULT_REJECTED_ON_DATE.toString())))
            .andExpect(jsonPath("$.[*].approvedOnDate").value(hasItem(DEFAULT_APPROVED_ON_DATE.toString())))
            .andExpect(jsonPath("$.[*].nominalAnnualInterestRate").value(hasItem(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].interestCompoundingPeriodType").value(hasItem(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE)))
            .andExpect(jsonPath("$.[*].interestPostingPeriodType").value(hasItem(DEFAULT_INTEREST_POSTING_PERIOD_TYPE)))
            .andExpect(jsonPath("$.[*].interestCalculationType").value(hasItem(DEFAULT_INTEREST_CALCULATION_TYPE)))
            .andExpect(jsonPath("$.[*].interestCalculationDaysInYearType").value(hasItem(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)))
            .andExpect(jsonPath("$.[*].minRequiredOpeningBalance").value(hasItem(DEFAULT_MIN_REQUIRED_OPENING_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].lockinPeriodFrequency").value(hasItem(DEFAULT_LOCKIN_PERIOD_FREQUENCY)))
            .andExpect(jsonPath("$.[*].lockinPeriodFrequencyType").value(hasItem(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE)))
            .andExpect(jsonPath("$.[*].nominalAnnualInterestRateOverdraft").value(hasItem(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT.intValue())))
            .andExpect(jsonPath("$.[*].minOverdraftForInterestCalculation").value(hasItem(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION.intValue())))
            .andExpect(jsonPath("$.[*].minBalanceForInterestCalculation").value(hasItem(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION.intValue())))
            .andExpect(jsonPath("$.[*].enforceMinRequiredBalance").value(hasItem(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].minRequiredBalance").value(hasItem(DEFAULT_MIN_REQUIRED_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].withdrawalFeeApplicableForTransfer").value(hasItem(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER.booleanValue())))
            .andExpect(jsonPath("$.[*].allowOverdraft").value(hasItem(DEFAULT_ALLOW_OVERDRAFT.booleanValue())))
            .andExpect(jsonPath("$.[*].overdraftLimit").value(hasItem(DEFAULT_OVERDRAFT_LIMIT.intValue())));
    }
    
    @Test
    @Transactional
    public void getSavingsAccount() throws Exception {
        // Initialize the database
        savingsAccountRepository.saveAndFlush(savingsAccount);

        // Get the savingsAccount
        restSavingsAccountMockMvc.perform(get("/api/savings-accounts/{id}", savingsAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(savingsAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.subStatus").value(DEFAULT_SUB_STATUS))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.submittedOnDate").value(DEFAULT_SUBMITTED_ON_DATE.toString()))
            .andExpect(jsonPath("$.rejectedOnDate").value(DEFAULT_REJECTED_ON_DATE.toString()))
            .andExpect(jsonPath("$.approvedOnDate").value(DEFAULT_APPROVED_ON_DATE.toString()))
            .andExpect(jsonPath("$.nominalAnnualInterestRate").value(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.interestCompoundingPeriodType").value(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE))
            .andExpect(jsonPath("$.interestPostingPeriodType").value(DEFAULT_INTEREST_POSTING_PERIOD_TYPE))
            .andExpect(jsonPath("$.interestCalculationType").value(DEFAULT_INTEREST_CALCULATION_TYPE))
            .andExpect(jsonPath("$.interestCalculationDaysInYearType").value(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE))
            .andExpect(jsonPath("$.minRequiredOpeningBalance").value(DEFAULT_MIN_REQUIRED_OPENING_BALANCE.intValue()))
            .andExpect(jsonPath("$.lockinPeriodFrequency").value(DEFAULT_LOCKIN_PERIOD_FREQUENCY))
            .andExpect(jsonPath("$.lockinPeriodFrequencyType").value(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE))
            .andExpect(jsonPath("$.nominalAnnualInterestRateOverdraft").value(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT.intValue()))
            .andExpect(jsonPath("$.minOverdraftForInterestCalculation").value(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION.intValue()))
            .andExpect(jsonPath("$.minBalanceForInterestCalculation").value(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION.intValue()))
            .andExpect(jsonPath("$.enforceMinRequiredBalance").value(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE.booleanValue()))
            .andExpect(jsonPath("$.minRequiredBalance").value(DEFAULT_MIN_REQUIRED_BALANCE.intValue()))
            .andExpect(jsonPath("$.withdrawalFeeApplicableForTransfer").value(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER.booleanValue()))
            .andExpect(jsonPath("$.allowOverdraft").value(DEFAULT_ALLOW_OVERDRAFT.booleanValue()))
            .andExpect(jsonPath("$.overdraftLimit").value(DEFAULT_OVERDRAFT_LIMIT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSavingsAccount() throws Exception {
        // Get the savingsAccount
        restSavingsAccountMockMvc.perform(get("/api/savings-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSavingsAccount() throws Exception {
        // Initialize the database
        savingsAccountRepository.saveAndFlush(savingsAccount);

        int databaseSizeBeforeUpdate = savingsAccountRepository.findAll().size();

        // Update the savingsAccount
        SavingsAccount updatedSavingsAccount = savingsAccountRepository.findById(savingsAccount.getId()).get();
        // Disconnect from session so that the updates on updatedSavingsAccount are not directly saved in db
        em.detach(updatedSavingsAccount);
        updatedSavingsAccount
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .externalId(UPDATED_EXTERNAL_ID)
            .status(UPDATED_STATUS)
            .subStatus(UPDATED_SUB_STATUS)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .submittedOnDate(UPDATED_SUBMITTED_ON_DATE)
            .rejectedOnDate(UPDATED_REJECTED_ON_DATE)
            .approvedOnDate(UPDATED_APPROVED_ON_DATE)
            .nominalAnnualInterestRate(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(UPDATED_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(UPDATED_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(UPDATED_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(UPDATED_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .nominalAnnualInterestRateOverdraft(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .minBalanceForInterestCalculation(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(UPDATED_MIN_REQUIRED_BALANCE)
            .withdrawalFeeApplicableForTransfer(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(UPDATED_ALLOW_OVERDRAFT)
            .overdraftLimit(UPDATED_OVERDRAFT_LIMIT);
        SavingsAccountDTO savingsAccountDTO = savingsAccountMapper.toDto(updatedSavingsAccount);

        restSavingsAccountMockMvc.perform(put("/api/savings-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountDTO)))
            .andExpect(status().isOk());

        // Validate the SavingsAccount in the database
        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeUpdate);
        SavingsAccount testSavingsAccount = savingsAccountList.get(savingsAccountList.size() - 1);
        assertThat(testSavingsAccount.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testSavingsAccount.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testSavingsAccount.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSavingsAccount.getSubStatus()).isEqualTo(UPDATED_SUB_STATUS);
        assertThat(testSavingsAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testSavingsAccount.getSubmittedOnDate()).isEqualTo(UPDATED_SUBMITTED_ON_DATE);
        assertThat(testSavingsAccount.getRejectedOnDate()).isEqualTo(UPDATED_REJECTED_ON_DATE);
        assertThat(testSavingsAccount.getApprovedOnDate()).isEqualTo(UPDATED_APPROVED_ON_DATE);
        assertThat(testSavingsAccount.getNominalAnnualInterestRate()).isEqualTo(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE);
        assertThat(testSavingsAccount.getInterestCompoundingPeriodType()).isEqualTo(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE);
        assertThat(testSavingsAccount.getInterestPostingPeriodType()).isEqualTo(UPDATED_INTEREST_POSTING_PERIOD_TYPE);
        assertThat(testSavingsAccount.getInterestCalculationType()).isEqualTo(UPDATED_INTEREST_CALCULATION_TYPE);
        assertThat(testSavingsAccount.getInterestCalculationDaysInYearType()).isEqualTo(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE);
        assertThat(testSavingsAccount.getMinRequiredOpeningBalance()).isEqualTo(UPDATED_MIN_REQUIRED_OPENING_BALANCE);
        assertThat(testSavingsAccount.getLockinPeriodFrequency()).isEqualTo(UPDATED_LOCKIN_PERIOD_FREQUENCY);
        assertThat(testSavingsAccount.getLockinPeriodFrequencyType()).isEqualTo(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE);
        assertThat(testSavingsAccount.getNominalAnnualInterestRateOverdraft()).isEqualTo(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT);
        assertThat(testSavingsAccount.getMinOverdraftForInterestCalculation()).isEqualTo(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsAccount.getMinBalanceForInterestCalculation()).isEqualTo(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsAccount.isEnforceMinRequiredBalance()).isEqualTo(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsAccount.getMinRequiredBalance()).isEqualTo(UPDATED_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsAccount.isWithdrawalFeeApplicableForTransfer()).isEqualTo(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER);
        assertThat(testSavingsAccount.isAllowOverdraft()).isEqualTo(UPDATED_ALLOW_OVERDRAFT);
        assertThat(testSavingsAccount.getOverdraftLimit()).isEqualTo(UPDATED_OVERDRAFT_LIMIT);
    }

    @Test
    @Transactional
    public void updateNonExistingSavingsAccount() throws Exception {
        int databaseSizeBeforeUpdate = savingsAccountRepository.findAll().size();

        // Create the SavingsAccount
        SavingsAccountDTO savingsAccountDTO = savingsAccountMapper.toDto(savingsAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSavingsAccountMockMvc.perform(put("/api/savings-accounts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsAccount in the database
        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSavingsAccount() throws Exception {
        // Initialize the database
        savingsAccountRepository.saveAndFlush(savingsAccount);

        int databaseSizeBeforeDelete = savingsAccountRepository.findAll().size();

        // Delete the savingsAccount
        restSavingsAccountMockMvc.perform(delete("/api/savings-accounts/{id}", savingsAccount.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findAll();
        assertThat(savingsAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

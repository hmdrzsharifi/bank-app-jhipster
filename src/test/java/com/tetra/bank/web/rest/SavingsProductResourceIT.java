package com.tetra.bank.web.rest;

import com.tetra.bank.BankApp;
import com.tetra.bank.domain.SavingsProduct;
import com.tetra.bank.repository.SavingsProductRepository;
import com.tetra.bank.service.SavingsProductService;
import com.tetra.bank.service.dto.SavingsProductDTO;
import com.tetra.bank.service.mapper.SavingsProductMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SavingsProductResource} REST controller.
 */
@SpringBootTest(classes = BankApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SavingsProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_ACCOUNTING_RULE = 1;
    private static final Integer UPDATED_ACCOUNTING_RULE = 2;

    private static final Boolean DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER = false;
    private static final Boolean UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER = true;

    private static final Boolean DEFAULT_ALLOW_OVERDRAFT = false;
    private static final Boolean UPDATED_ALLOW_OVERDRAFT = true;

    private static final BigDecimal DEFAULT_OVERDRAFT_LIMIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_OVERDRAFT_LIMIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT = new BigDecimal(1);
    private static final BigDecimal UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION = new BigDecimal(2);

    private static final Boolean DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE = false;
    private static final Boolean UPDATED_ENFORCE_MIN_REQUIRED_BALANCE = true;

    private static final BigDecimal DEFAULT_MIN_REQUIRED_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_REQUIRED_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION = new BigDecimal(2);

    private static final Boolean DEFAULT_WITH_HOLD_TAX = false;
    private static final Boolean UPDATED_WITH_HOLD_TAX = true;

    private static final Boolean DEFAULT_IS_DORMANCY_TRACKING_ACTIVE = false;
    private static final Boolean UPDATED_IS_DORMANCY_TRACKING_ACTIVE = true;

    private static final Long DEFAULT_DAYS_TO_INACTIVE = 1L;
    private static final Long UPDATED_DAYS_TO_INACTIVE = 2L;

    private static final Long DEFAULT_DAYS_TO_DORMANCY = 1L;
    private static final Long UPDATED_DAYS_TO_DORMANCY = 2L;

    private static final Long DEFAULT_DAYS_TO_ESCHEAT = 1L;
    private static final Long UPDATED_DAYS_TO_ESCHEAT = 2L;

    @Autowired
    private SavingsProductRepository savingsProductRepository;

    @Autowired
    private SavingsProductMapper savingsProductMapper;

    @Autowired
    private SavingsProductService savingsProductService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSavingsProductMockMvc;

    private SavingsProduct savingsProduct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsProduct createEntity(EntityManager em) {
        SavingsProduct savingsProduct = new SavingsProduct()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .nominalAnnualInterestRate(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(DEFAULT_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(DEFAULT_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(DEFAULT_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(DEFAULT_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .accountingRule(DEFAULT_ACCOUNTING_RULE)
            .withdrawalFeeApplicableForTransfer(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(DEFAULT_ALLOW_OVERDRAFT)
            .overdraftLimit(DEFAULT_OVERDRAFT_LIMIT)
            .nominalAnnualInterestRateOverdraft(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(DEFAULT_MIN_REQUIRED_BALANCE)
            .minBalanceForInterestCalculation(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .withHoldTax(DEFAULT_WITH_HOLD_TAX)
            .isDormancyTrackingActive(DEFAULT_IS_DORMANCY_TRACKING_ACTIVE)
            .daysToInactive(DEFAULT_DAYS_TO_INACTIVE)
            .daysToDormancy(DEFAULT_DAYS_TO_DORMANCY)
            .daysToEscheat(DEFAULT_DAYS_TO_ESCHEAT);
        return savingsProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SavingsProduct createUpdatedEntity(EntityManager em) {
        SavingsProduct savingsProduct = new SavingsProduct()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .nominalAnnualInterestRate(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(UPDATED_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(UPDATED_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(UPDATED_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(UPDATED_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .accountingRule(UPDATED_ACCOUNTING_RULE)
            .withdrawalFeeApplicableForTransfer(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(UPDATED_ALLOW_OVERDRAFT)
            .overdraftLimit(UPDATED_OVERDRAFT_LIMIT)
            .nominalAnnualInterestRateOverdraft(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(UPDATED_MIN_REQUIRED_BALANCE)
            .minBalanceForInterestCalculation(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .withHoldTax(UPDATED_WITH_HOLD_TAX)
            .isDormancyTrackingActive(UPDATED_IS_DORMANCY_TRACKING_ACTIVE)
            .daysToInactive(UPDATED_DAYS_TO_INACTIVE)
            .daysToDormancy(UPDATED_DAYS_TO_DORMANCY)
            .daysToEscheat(UPDATED_DAYS_TO_ESCHEAT);
        return savingsProduct;
    }

    @BeforeEach
    public void initTest() {
        savingsProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createSavingsProduct() throws Exception {
        int databaseSizeBeforeCreate = savingsProductRepository.findAll().size();
        // Create the SavingsProduct
        SavingsProductDTO savingsProductDTO = savingsProductMapper.toDto(savingsProduct);
        restSavingsProductMockMvc.perform(post("/api/savings-products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsProductDTO)))
            .andExpect(status().isCreated());

        // Validate the SavingsProduct in the database
        List<SavingsProduct> savingsProductList = savingsProductRepository.findAll();
        assertThat(savingsProductList).hasSize(databaseSizeBeforeCreate + 1);
        SavingsProduct testSavingsProduct = savingsProductList.get(savingsProductList.size() - 1);
        assertThat(testSavingsProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSavingsProduct.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testSavingsProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSavingsProduct.getNominalAnnualInterestRate()).isEqualTo(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE);
        assertThat(testSavingsProduct.getInterestCompoundingPeriodType()).isEqualTo(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE);
        assertThat(testSavingsProduct.getInterestPostingPeriodType()).isEqualTo(DEFAULT_INTEREST_POSTING_PERIOD_TYPE);
        assertThat(testSavingsProduct.getInterestCalculationType()).isEqualTo(DEFAULT_INTEREST_CALCULATION_TYPE);
        assertThat(testSavingsProduct.getInterestCalculationDaysInYearType()).isEqualTo(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE);
        assertThat(testSavingsProduct.getMinRequiredOpeningBalance()).isEqualTo(DEFAULT_MIN_REQUIRED_OPENING_BALANCE);
        assertThat(testSavingsProduct.getLockinPeriodFrequency()).isEqualTo(DEFAULT_LOCKIN_PERIOD_FREQUENCY);
        assertThat(testSavingsProduct.getLockinPeriodFrequencyType()).isEqualTo(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE);
        assertThat(testSavingsProduct.getAccountingRule()).isEqualTo(DEFAULT_ACCOUNTING_RULE);
        assertThat(testSavingsProduct.isWithdrawalFeeApplicableForTransfer()).isEqualTo(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER);
        assertThat(testSavingsProduct.isAllowOverdraft()).isEqualTo(DEFAULT_ALLOW_OVERDRAFT);
        assertThat(testSavingsProduct.getOverdraftLimit()).isEqualTo(DEFAULT_OVERDRAFT_LIMIT);
        assertThat(testSavingsProduct.getNominalAnnualInterestRateOverdraft()).isEqualTo(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT);
        assertThat(testSavingsProduct.getMinOverdraftForInterestCalculation()).isEqualTo(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsProduct.isEnforceMinRequiredBalance()).isEqualTo(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsProduct.getMinRequiredBalance()).isEqualTo(DEFAULT_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsProduct.getMinBalanceForInterestCalculation()).isEqualTo(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsProduct.isWithHoldTax()).isEqualTo(DEFAULT_WITH_HOLD_TAX);
        assertThat(testSavingsProduct.isIsDormancyTrackingActive()).isEqualTo(DEFAULT_IS_DORMANCY_TRACKING_ACTIVE);
        assertThat(testSavingsProduct.getDaysToInactive()).isEqualTo(DEFAULT_DAYS_TO_INACTIVE);
        assertThat(testSavingsProduct.getDaysToDormancy()).isEqualTo(DEFAULT_DAYS_TO_DORMANCY);
        assertThat(testSavingsProduct.getDaysToEscheat()).isEqualTo(DEFAULT_DAYS_TO_ESCHEAT);
    }

    @Test
    @Transactional
    public void createSavingsProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = savingsProductRepository.findAll().size();

        // Create the SavingsProduct with an existing ID
        savingsProduct.setId(1L);
        SavingsProductDTO savingsProductDTO = savingsProductMapper.toDto(savingsProduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSavingsProductMockMvc.perform(post("/api/savings-products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsProduct in the database
        List<SavingsProduct> savingsProductList = savingsProductRepository.findAll();
        assertThat(savingsProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSavingsProducts() throws Exception {
        // Initialize the database
        savingsProductRepository.saveAndFlush(savingsProduct);

        // Get all the savingsProductList
        restSavingsProductMockMvc.perform(get("/api/savings-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(savingsProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].nominalAnnualInterestRate").value(hasItem(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].interestCompoundingPeriodType").value(hasItem(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE)))
            .andExpect(jsonPath("$.[*].interestPostingPeriodType").value(hasItem(DEFAULT_INTEREST_POSTING_PERIOD_TYPE)))
            .andExpect(jsonPath("$.[*].interestCalculationType").value(hasItem(DEFAULT_INTEREST_CALCULATION_TYPE)))
            .andExpect(jsonPath("$.[*].interestCalculationDaysInYearType").value(hasItem(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)))
            .andExpect(jsonPath("$.[*].minRequiredOpeningBalance").value(hasItem(DEFAULT_MIN_REQUIRED_OPENING_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].lockinPeriodFrequency").value(hasItem(DEFAULT_LOCKIN_PERIOD_FREQUENCY)))
            .andExpect(jsonPath("$.[*].lockinPeriodFrequencyType").value(hasItem(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE)))
            .andExpect(jsonPath("$.[*].accountingRule").value(hasItem(DEFAULT_ACCOUNTING_RULE)))
            .andExpect(jsonPath("$.[*].withdrawalFeeApplicableForTransfer").value(hasItem(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER.booleanValue())))
            .andExpect(jsonPath("$.[*].allowOverdraft").value(hasItem(DEFAULT_ALLOW_OVERDRAFT.booleanValue())))
            .andExpect(jsonPath("$.[*].overdraftLimit").value(hasItem(DEFAULT_OVERDRAFT_LIMIT.intValue())))
            .andExpect(jsonPath("$.[*].nominalAnnualInterestRateOverdraft").value(hasItem(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT.intValue())))
            .andExpect(jsonPath("$.[*].minOverdraftForInterestCalculation").value(hasItem(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION.intValue())))
            .andExpect(jsonPath("$.[*].enforceMinRequiredBalance").value(hasItem(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].minRequiredBalance").value(hasItem(DEFAULT_MIN_REQUIRED_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].minBalanceForInterestCalculation").value(hasItem(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION.intValue())))
            .andExpect(jsonPath("$.[*].withHoldTax").value(hasItem(DEFAULT_WITH_HOLD_TAX.booleanValue())))
            .andExpect(jsonPath("$.[*].isDormancyTrackingActive").value(hasItem(DEFAULT_IS_DORMANCY_TRACKING_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].daysToInactive").value(hasItem(DEFAULT_DAYS_TO_INACTIVE.intValue())))
            .andExpect(jsonPath("$.[*].daysToDormancy").value(hasItem(DEFAULT_DAYS_TO_DORMANCY.intValue())))
            .andExpect(jsonPath("$.[*].daysToEscheat").value(hasItem(DEFAULT_DAYS_TO_ESCHEAT.intValue())));
    }
    
    @Test
    @Transactional
    public void getSavingsProduct() throws Exception {
        // Initialize the database
        savingsProductRepository.saveAndFlush(savingsProduct);

        // Get the savingsProduct
        restSavingsProductMockMvc.perform(get("/api/savings-products/{id}", savingsProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(savingsProduct.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.nominalAnnualInterestRate").value(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.interestCompoundingPeriodType").value(DEFAULT_INTEREST_COMPOUNDING_PERIOD_TYPE))
            .andExpect(jsonPath("$.interestPostingPeriodType").value(DEFAULT_INTEREST_POSTING_PERIOD_TYPE))
            .andExpect(jsonPath("$.interestCalculationType").value(DEFAULT_INTEREST_CALCULATION_TYPE))
            .andExpect(jsonPath("$.interestCalculationDaysInYearType").value(DEFAULT_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE))
            .andExpect(jsonPath("$.minRequiredOpeningBalance").value(DEFAULT_MIN_REQUIRED_OPENING_BALANCE.intValue()))
            .andExpect(jsonPath("$.lockinPeriodFrequency").value(DEFAULT_LOCKIN_PERIOD_FREQUENCY))
            .andExpect(jsonPath("$.lockinPeriodFrequencyType").value(DEFAULT_LOCKIN_PERIOD_FREQUENCY_TYPE))
            .andExpect(jsonPath("$.accountingRule").value(DEFAULT_ACCOUNTING_RULE))
            .andExpect(jsonPath("$.withdrawalFeeApplicableForTransfer").value(DEFAULT_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER.booleanValue()))
            .andExpect(jsonPath("$.allowOverdraft").value(DEFAULT_ALLOW_OVERDRAFT.booleanValue()))
            .andExpect(jsonPath("$.overdraftLimit").value(DEFAULT_OVERDRAFT_LIMIT.intValue()))
            .andExpect(jsonPath("$.nominalAnnualInterestRateOverdraft").value(DEFAULT_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT.intValue()))
            .andExpect(jsonPath("$.minOverdraftForInterestCalculation").value(DEFAULT_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION.intValue()))
            .andExpect(jsonPath("$.enforceMinRequiredBalance").value(DEFAULT_ENFORCE_MIN_REQUIRED_BALANCE.booleanValue()))
            .andExpect(jsonPath("$.minRequiredBalance").value(DEFAULT_MIN_REQUIRED_BALANCE.intValue()))
            .andExpect(jsonPath("$.minBalanceForInterestCalculation").value(DEFAULT_MIN_BALANCE_FOR_INTEREST_CALCULATION.intValue()))
            .andExpect(jsonPath("$.withHoldTax").value(DEFAULT_WITH_HOLD_TAX.booleanValue()))
            .andExpect(jsonPath("$.isDormancyTrackingActive").value(DEFAULT_IS_DORMANCY_TRACKING_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.daysToInactive").value(DEFAULT_DAYS_TO_INACTIVE.intValue()))
            .andExpect(jsonPath("$.daysToDormancy").value(DEFAULT_DAYS_TO_DORMANCY.intValue()))
            .andExpect(jsonPath("$.daysToEscheat").value(DEFAULT_DAYS_TO_ESCHEAT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSavingsProduct() throws Exception {
        // Get the savingsProduct
        restSavingsProductMockMvc.perform(get("/api/savings-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSavingsProduct() throws Exception {
        // Initialize the database
        savingsProductRepository.saveAndFlush(savingsProduct);

        int databaseSizeBeforeUpdate = savingsProductRepository.findAll().size();

        // Update the savingsProduct
        SavingsProduct updatedSavingsProduct = savingsProductRepository.findById(savingsProduct.getId()).get();
        // Disconnect from session so that the updates on updatedSavingsProduct are not directly saved in db
        em.detach(updatedSavingsProduct);
        updatedSavingsProduct
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .nominalAnnualInterestRate(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE)
            .interestCompoundingPeriodType(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE)
            .interestPostingPeriodType(UPDATED_INTEREST_POSTING_PERIOD_TYPE)
            .interestCalculationType(UPDATED_INTEREST_CALCULATION_TYPE)
            .interestCalculationDaysInYearType(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE)
            .minRequiredOpeningBalance(UPDATED_MIN_REQUIRED_OPENING_BALANCE)
            .lockinPeriodFrequency(UPDATED_LOCKIN_PERIOD_FREQUENCY)
            .lockinPeriodFrequencyType(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE)
            .accountingRule(UPDATED_ACCOUNTING_RULE)
            .withdrawalFeeApplicableForTransfer(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER)
            .allowOverdraft(UPDATED_ALLOW_OVERDRAFT)
            .overdraftLimit(UPDATED_OVERDRAFT_LIMIT)
            .nominalAnnualInterestRateOverdraft(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT)
            .minOverdraftForInterestCalculation(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION)
            .enforceMinRequiredBalance(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE)
            .minRequiredBalance(UPDATED_MIN_REQUIRED_BALANCE)
            .minBalanceForInterestCalculation(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION)
            .withHoldTax(UPDATED_WITH_HOLD_TAX)
            .isDormancyTrackingActive(UPDATED_IS_DORMANCY_TRACKING_ACTIVE)
            .daysToInactive(UPDATED_DAYS_TO_INACTIVE)
            .daysToDormancy(UPDATED_DAYS_TO_DORMANCY)
            .daysToEscheat(UPDATED_DAYS_TO_ESCHEAT);
        SavingsProductDTO savingsProductDTO = savingsProductMapper.toDto(updatedSavingsProduct);

        restSavingsProductMockMvc.perform(put("/api/savings-products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsProductDTO)))
            .andExpect(status().isOk());

        // Validate the SavingsProduct in the database
        List<SavingsProduct> savingsProductList = savingsProductRepository.findAll();
        assertThat(savingsProductList).hasSize(databaseSizeBeforeUpdate);
        SavingsProduct testSavingsProduct = savingsProductList.get(savingsProductList.size() - 1);
        assertThat(testSavingsProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSavingsProduct.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testSavingsProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSavingsProduct.getNominalAnnualInterestRate()).isEqualTo(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE);
        assertThat(testSavingsProduct.getInterestCompoundingPeriodType()).isEqualTo(UPDATED_INTEREST_COMPOUNDING_PERIOD_TYPE);
        assertThat(testSavingsProduct.getInterestPostingPeriodType()).isEqualTo(UPDATED_INTEREST_POSTING_PERIOD_TYPE);
        assertThat(testSavingsProduct.getInterestCalculationType()).isEqualTo(UPDATED_INTEREST_CALCULATION_TYPE);
        assertThat(testSavingsProduct.getInterestCalculationDaysInYearType()).isEqualTo(UPDATED_INTEREST_CALCULATION_DAYS_IN_YEAR_TYPE);
        assertThat(testSavingsProduct.getMinRequiredOpeningBalance()).isEqualTo(UPDATED_MIN_REQUIRED_OPENING_BALANCE);
        assertThat(testSavingsProduct.getLockinPeriodFrequency()).isEqualTo(UPDATED_LOCKIN_PERIOD_FREQUENCY);
        assertThat(testSavingsProduct.getLockinPeriodFrequencyType()).isEqualTo(UPDATED_LOCKIN_PERIOD_FREQUENCY_TYPE);
        assertThat(testSavingsProduct.getAccountingRule()).isEqualTo(UPDATED_ACCOUNTING_RULE);
        assertThat(testSavingsProduct.isWithdrawalFeeApplicableForTransfer()).isEqualTo(UPDATED_WITHDRAWAL_FEE_APPLICABLE_FOR_TRANSFER);
        assertThat(testSavingsProduct.isAllowOverdraft()).isEqualTo(UPDATED_ALLOW_OVERDRAFT);
        assertThat(testSavingsProduct.getOverdraftLimit()).isEqualTo(UPDATED_OVERDRAFT_LIMIT);
        assertThat(testSavingsProduct.getNominalAnnualInterestRateOverdraft()).isEqualTo(UPDATED_NOMINAL_ANNUAL_INTEREST_RATE_OVERDRAFT);
        assertThat(testSavingsProduct.getMinOverdraftForInterestCalculation()).isEqualTo(UPDATED_MIN_OVERDRAFT_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsProduct.isEnforceMinRequiredBalance()).isEqualTo(UPDATED_ENFORCE_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsProduct.getMinRequiredBalance()).isEqualTo(UPDATED_MIN_REQUIRED_BALANCE);
        assertThat(testSavingsProduct.getMinBalanceForInterestCalculation()).isEqualTo(UPDATED_MIN_BALANCE_FOR_INTEREST_CALCULATION);
        assertThat(testSavingsProduct.isWithHoldTax()).isEqualTo(UPDATED_WITH_HOLD_TAX);
        assertThat(testSavingsProduct.isIsDormancyTrackingActive()).isEqualTo(UPDATED_IS_DORMANCY_TRACKING_ACTIVE);
        assertThat(testSavingsProduct.getDaysToInactive()).isEqualTo(UPDATED_DAYS_TO_INACTIVE);
        assertThat(testSavingsProduct.getDaysToDormancy()).isEqualTo(UPDATED_DAYS_TO_DORMANCY);
        assertThat(testSavingsProduct.getDaysToEscheat()).isEqualTo(UPDATED_DAYS_TO_ESCHEAT);
    }

    @Test
    @Transactional
    public void updateNonExistingSavingsProduct() throws Exception {
        int databaseSizeBeforeUpdate = savingsProductRepository.findAll().size();

        // Create the SavingsProduct
        SavingsProductDTO savingsProductDTO = savingsProductMapper.toDto(savingsProduct);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSavingsProductMockMvc.perform(put("/api/savings-products").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(savingsProductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SavingsProduct in the database
        List<SavingsProduct> savingsProductList = savingsProductRepository.findAll();
        assertThat(savingsProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSavingsProduct() throws Exception {
        // Initialize the database
        savingsProductRepository.saveAndFlush(savingsProduct);

        int databaseSizeBeforeDelete = savingsProductRepository.findAll().size();

        // Delete the savingsProduct
        restSavingsProductMockMvc.perform(delete("/api/savings-products/{id}", savingsProduct.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SavingsProduct> savingsProductList = savingsProductRepository.findAll();
        assertThat(savingsProductList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.tetra.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The SavingsProduct entity.\n@author HamidReza.
 */
@Entity
@Table(name = "m_savings_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SavingsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    
    @Column(name = "name", unique = true)
    private String name;

    
    @Column(name = "short_name", unique = true)
    private String shortName;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "nominal_annual_interest_rate", precision = 21, scale = 2)
    private BigDecimal nominalAnnualInterestRate;

    @Column(name = "interest_compounding_period_type")
    private Integer interestCompoundingPeriodType;

    @Column(name = "interest_posting_period_type")
    private Integer interestPostingPeriodType;

    @Column(name = "interest_calculation_type")
    private Integer interestCalculationType;

    @Column(name = "interest_calculation_days_in_year_type")
    private Integer interestCalculationDaysInYearType;

    @Column(name = "min_required_opening_balance", precision = 21, scale = 2)
    private BigDecimal minRequiredOpeningBalance;

    @Column(name = "lockin_period_frequency")
    private Integer lockinPeriodFrequency;

    @Column(name = "lockin_period_frequency_type")
    private Integer lockinPeriodFrequencyType;

    @Column(name = "accounting_rule")
    private Integer accountingRule;

    @Column(name = "withdrawal_fee_applicable_for_transfer")
    private Boolean withdrawalFeeApplicableForTransfer;

    @Column(name = "allow_overdraft")
    private Boolean allowOverdraft;

    @Column(name = "overdraft_limit", precision = 21, scale = 2)
    private BigDecimal overdraftLimit;

    @Column(name = "nominal_annual_interest_rate_overdraft", precision = 21, scale = 2)
    private BigDecimal nominalAnnualInterestRateOverdraft;

    @Column(name = "min_overdraft_for_interest_calculation", precision = 21, scale = 2)
    private BigDecimal minOverdraftForInterestCalculation;

    @Column(name = "enforce_min_required_balance")
    private Boolean enforceMinRequiredBalance;

    @Column(name = "min_required_balance", precision = 21, scale = 2)
    private BigDecimal minRequiredBalance;

    @Column(name = "min_balance_for_interest_calculation", precision = 21, scale = 2)
    private BigDecimal minBalanceForInterestCalculation;

    @Column(name = "with_hold_tax")
    private Boolean withHoldTax;

    @Column(name = "is_dormancy_tracking_active")
    private Boolean isDormancyTrackingActive;

    @Column(name = "days_to_inactive")
    private Long daysToInactive;

    @Column(name = "days_to_dormancy")
    private Long daysToDormancy;

    @Column(name = "days_to_escheat")
    private Long daysToEscheat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SavingsProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public SavingsProduct shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public SavingsProduct description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getNominalAnnualInterestRate() {
        return nominalAnnualInterestRate;
    }

    public SavingsProduct nominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
        return this;
    }

    public void setNominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
    }

    public Integer getInterestCompoundingPeriodType() {
        return interestCompoundingPeriodType;
    }

    public SavingsProduct interestCompoundingPeriodType(Integer interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
        return this;
    }

    public void setInterestCompoundingPeriodType(Integer interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
    }

    public Integer getInterestPostingPeriodType() {
        return interestPostingPeriodType;
    }

    public SavingsProduct interestPostingPeriodType(Integer interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
        return this;
    }

    public void setInterestPostingPeriodType(Integer interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
    }

    public Integer getInterestCalculationType() {
        return interestCalculationType;
    }

    public SavingsProduct interestCalculationType(Integer interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
        return this;
    }

    public void setInterestCalculationType(Integer interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
    }

    public Integer getInterestCalculationDaysInYearType() {
        return interestCalculationDaysInYearType;
    }

    public SavingsProduct interestCalculationDaysInYearType(Integer interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
        return this;
    }

    public void setInterestCalculationDaysInYearType(Integer interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
    }

    public BigDecimal getMinRequiredOpeningBalance() {
        return minRequiredOpeningBalance;
    }

    public SavingsProduct minRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
        return this;
    }

    public void setMinRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
    }

    public Integer getLockinPeriodFrequency() {
        return lockinPeriodFrequency;
    }

    public SavingsProduct lockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
        return this;
    }

    public void setLockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
    }

    public Integer getLockinPeriodFrequencyType() {
        return lockinPeriodFrequencyType;
    }

    public SavingsProduct lockinPeriodFrequencyType(Integer lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
        return this;
    }

    public void setLockinPeriodFrequencyType(Integer lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
    }

    public Integer getAccountingRule() {
        return accountingRule;
    }

    public SavingsProduct accountingRule(Integer accountingRule) {
        this.accountingRule = accountingRule;
        return this;
    }

    public void setAccountingRule(Integer accountingRule) {
        this.accountingRule = accountingRule;
    }

    public Boolean isWithdrawalFeeApplicableForTransfer() {
        return withdrawalFeeApplicableForTransfer;
    }

    public SavingsProduct withdrawalFeeApplicableForTransfer(Boolean withdrawalFeeApplicableForTransfer) {
        this.withdrawalFeeApplicableForTransfer = withdrawalFeeApplicableForTransfer;
        return this;
    }

    public void setWithdrawalFeeApplicableForTransfer(Boolean withdrawalFeeApplicableForTransfer) {
        this.withdrawalFeeApplicableForTransfer = withdrawalFeeApplicableForTransfer;
    }

    public Boolean isAllowOverdraft() {
        return allowOverdraft;
    }

    public SavingsProduct allowOverdraft(Boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
        return this;
    }

    public void setAllowOverdraft(Boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public SavingsProduct overdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return this;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public BigDecimal getNominalAnnualInterestRateOverdraft() {
        return nominalAnnualInterestRateOverdraft;
    }

    public SavingsProduct nominalAnnualInterestRateOverdraft(BigDecimal nominalAnnualInterestRateOverdraft) {
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
        return this;
    }

    public void setNominalAnnualInterestRateOverdraft(BigDecimal nominalAnnualInterestRateOverdraft) {
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
    }

    public BigDecimal getMinOverdraftForInterestCalculation() {
        return minOverdraftForInterestCalculation;
    }

    public SavingsProduct minOverdraftForInterestCalculation(BigDecimal minOverdraftForInterestCalculation) {
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
        return this;
    }

    public void setMinOverdraftForInterestCalculation(BigDecimal minOverdraftForInterestCalculation) {
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
    }

    public Boolean isEnforceMinRequiredBalance() {
        return enforceMinRequiredBalance;
    }

    public SavingsProduct enforceMinRequiredBalance(Boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
        return this;
    }

    public void setEnforceMinRequiredBalance(Boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
    }

    public BigDecimal getMinRequiredBalance() {
        return minRequiredBalance;
    }

    public SavingsProduct minRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
        return this;
    }

    public void setMinRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
    }

    public BigDecimal getMinBalanceForInterestCalculation() {
        return minBalanceForInterestCalculation;
    }

    public SavingsProduct minBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
        return this;
    }

    public void setMinBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
    }

    public Boolean isWithHoldTax() {
        return withHoldTax;
    }

    public SavingsProduct withHoldTax(Boolean withHoldTax) {
        this.withHoldTax = withHoldTax;
        return this;
    }

    public void setWithHoldTax(Boolean withHoldTax) {
        this.withHoldTax = withHoldTax;
    }

    public Boolean isIsDormancyTrackingActive() {
        return isDormancyTrackingActive;
    }

    public SavingsProduct isDormancyTrackingActive(Boolean isDormancyTrackingActive) {
        this.isDormancyTrackingActive = isDormancyTrackingActive;
        return this;
    }

    public void setIsDormancyTrackingActive(Boolean isDormancyTrackingActive) {
        this.isDormancyTrackingActive = isDormancyTrackingActive;
    }

    public Long getDaysToInactive() {
        return daysToInactive;
    }

    public SavingsProduct daysToInactive(Long daysToInactive) {
        this.daysToInactive = daysToInactive;
        return this;
    }

    public void setDaysToInactive(Long daysToInactive) {
        this.daysToInactive = daysToInactive;
    }

    public Long getDaysToDormancy() {
        return daysToDormancy;
    }

    public SavingsProduct daysToDormancy(Long daysToDormancy) {
        this.daysToDormancy = daysToDormancy;
        return this;
    }

    public void setDaysToDormancy(Long daysToDormancy) {
        this.daysToDormancy = daysToDormancy;
    }

    public Long getDaysToEscheat() {
        return daysToEscheat;
    }

    public SavingsProduct daysToEscheat(Long daysToEscheat) {
        this.daysToEscheat = daysToEscheat;
        return this;
    }

    public void setDaysToEscheat(Long daysToEscheat) {
        this.daysToEscheat = daysToEscheat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsProduct)) {
            return false;
        }
        return id != null && id.equals(((SavingsProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsProduct{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", nominalAnnualInterestRate=" + getNominalAnnualInterestRate() +
            ", interestCompoundingPeriodType=" + getInterestCompoundingPeriodType() +
            ", interestPostingPeriodType=" + getInterestPostingPeriodType() +
            ", interestCalculationType=" + getInterestCalculationType() +
            ", interestCalculationDaysInYearType=" + getInterestCalculationDaysInYearType() +
            ", minRequiredOpeningBalance=" + getMinRequiredOpeningBalance() +
            ", lockinPeriodFrequency=" + getLockinPeriodFrequency() +
            ", lockinPeriodFrequencyType=" + getLockinPeriodFrequencyType() +
            ", accountingRule=" + getAccountingRule() +
            ", withdrawalFeeApplicableForTransfer='" + isWithdrawalFeeApplicableForTransfer() + "'" +
            ", allowOverdraft='" + isAllowOverdraft() + "'" +
            ", overdraftLimit=" + getOverdraftLimit() +
            ", nominalAnnualInterestRateOverdraft=" + getNominalAnnualInterestRateOverdraft() +
            ", minOverdraftForInterestCalculation=" + getMinOverdraftForInterestCalculation() +
            ", enforceMinRequiredBalance='" + isEnforceMinRequiredBalance() + "'" +
            ", minRequiredBalance=" + getMinRequiredBalance() +
            ", minBalanceForInterestCalculation=" + getMinBalanceForInterestCalculation() +
            ", withHoldTax='" + isWithHoldTax() + "'" +
            ", isDormancyTrackingActive='" + isIsDormancyTrackingActive() + "'" +
            ", daysToInactive=" + getDaysToInactive() +
            ", daysToDormancy=" + getDaysToDormancy() +
            ", daysToEscheat=" + getDaysToEscheat() +
            "}";
    }
}

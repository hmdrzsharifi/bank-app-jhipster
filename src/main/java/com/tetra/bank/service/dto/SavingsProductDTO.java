package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.tetra.bank.domain.SavingsProduct} entity.
 */
@ApiModel(description = "The SavingsProduct entity.\n@author HamidReza.")
public class SavingsProductDTO implements Serializable {
    
    private Long id;

    
    private String name;

    
    private String shortName;

    @Size(max = 500)
    private String description;

    private BigDecimal nominalAnnualInterestRate;

    private Integer interestCompoundingPeriodType;

    private Integer interestPostingPeriodType;

    private Integer interestCalculationType;

    private Integer interestCalculationDaysInYearType;

    private BigDecimal minRequiredOpeningBalance;

    private Integer lockinPeriodFrequency;

    private Integer lockinPeriodFrequencyType;

    private Integer accountingRule;

    private Boolean withdrawalFeeApplicableForTransfer;

    private Boolean allowOverdraft;

    private BigDecimal overdraftLimit;

    private BigDecimal nominalAnnualInterestRateOverdraft;

    private BigDecimal minOverdraftForInterestCalculation;

    private Boolean enforceMinRequiredBalance;

    private BigDecimal minRequiredBalance;

    private BigDecimal minBalanceForInterestCalculation;

    private Boolean withHoldTax;

    private Boolean isDormancyTrackingActive;

    private Long daysToInactive;

    private Long daysToDormancy;

    private Long daysToEscheat;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getNominalAnnualInterestRate() {
        return nominalAnnualInterestRate;
    }

    public void setNominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
    }

    public Integer getInterestCompoundingPeriodType() {
        return interestCompoundingPeriodType;
    }

    public void setInterestCompoundingPeriodType(Integer interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
    }

    public Integer getInterestPostingPeriodType() {
        return interestPostingPeriodType;
    }

    public void setInterestPostingPeriodType(Integer interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
    }

    public Integer getInterestCalculationType() {
        return interestCalculationType;
    }

    public void setInterestCalculationType(Integer interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
    }

    public Integer getInterestCalculationDaysInYearType() {
        return interestCalculationDaysInYearType;
    }

    public void setInterestCalculationDaysInYearType(Integer interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
    }

    public BigDecimal getMinRequiredOpeningBalance() {
        return minRequiredOpeningBalance;
    }

    public void setMinRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
    }

    public Integer getLockinPeriodFrequency() {
        return lockinPeriodFrequency;
    }

    public void setLockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
    }

    public Integer getLockinPeriodFrequencyType() {
        return lockinPeriodFrequencyType;
    }

    public void setLockinPeriodFrequencyType(Integer lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
    }

    public Integer getAccountingRule() {
        return accountingRule;
    }

    public void setAccountingRule(Integer accountingRule) {
        this.accountingRule = accountingRule;
    }

    public Boolean isWithdrawalFeeApplicableForTransfer() {
        return withdrawalFeeApplicableForTransfer;
    }

    public void setWithdrawalFeeApplicableForTransfer(Boolean withdrawalFeeApplicableForTransfer) {
        this.withdrawalFeeApplicableForTransfer = withdrawalFeeApplicableForTransfer;
    }

    public Boolean isAllowOverdraft() {
        return allowOverdraft;
    }

    public void setAllowOverdraft(Boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public BigDecimal getNominalAnnualInterestRateOverdraft() {
        return nominalAnnualInterestRateOverdraft;
    }

    public void setNominalAnnualInterestRateOverdraft(BigDecimal nominalAnnualInterestRateOverdraft) {
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
    }

    public BigDecimal getMinOverdraftForInterestCalculation() {
        return minOverdraftForInterestCalculation;
    }

    public void setMinOverdraftForInterestCalculation(BigDecimal minOverdraftForInterestCalculation) {
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
    }

    public Boolean isEnforceMinRequiredBalance() {
        return enforceMinRequiredBalance;
    }

    public void setEnforceMinRequiredBalance(Boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
    }

    public BigDecimal getMinRequiredBalance() {
        return minRequiredBalance;
    }

    public void setMinRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
    }

    public BigDecimal getMinBalanceForInterestCalculation() {
        return minBalanceForInterestCalculation;
    }

    public void setMinBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
    }

    public Boolean isWithHoldTax() {
        return withHoldTax;
    }

    public void setWithHoldTax(Boolean withHoldTax) {
        this.withHoldTax = withHoldTax;
    }

    public Boolean isIsDormancyTrackingActive() {
        return isDormancyTrackingActive;
    }

    public void setIsDormancyTrackingActive(Boolean isDormancyTrackingActive) {
        this.isDormancyTrackingActive = isDormancyTrackingActive;
    }

    public Long getDaysToInactive() {
        return daysToInactive;
    }

    public void setDaysToInactive(Long daysToInactive) {
        this.daysToInactive = daysToInactive;
    }

    public Long getDaysToDormancy() {
        return daysToDormancy;
    }

    public void setDaysToDormancy(Long daysToDormancy) {
        this.daysToDormancy = daysToDormancy;
    }

    public Long getDaysToEscheat() {
        return daysToEscheat;
    }

    public void setDaysToEscheat(Long daysToEscheat) {
        this.daysToEscheat = daysToEscheat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsProductDTO)) {
            return false;
        }

        return id != null && id.equals(((SavingsProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsProductDTO{" +
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

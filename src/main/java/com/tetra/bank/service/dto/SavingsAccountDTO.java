package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.tetra.bank.domain.SavingsAccount} entity.
 */
@ApiModel(description = "The SavingsAccount entity.\n@author HamidReza.")
public class SavingsAccountDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String accountNumber;

    private String externalId;

    private Integer status;

    private Integer subStatus;

    private Integer accountType;

    private Instant submittedOnDate;

    private Instant rejectedOnDate;

    private Instant approvedOnDate;

    private BigDecimal nominalAnnualInterestRate;

    private Integer interestCompoundingPeriodType;

    private Integer interestPostingPeriodType;

    private Integer interestCalculationType;

    private Integer interestCalculationDaysInYearType;

    private BigDecimal minRequiredOpeningBalance;

    private Integer lockinPeriodFrequency;

    private Integer lockinPeriodFrequencyType;

    private BigDecimal nominalAnnualInterestRateOverdraft;

    private BigDecimal minOverdraftForInterestCalculation;

    private BigDecimal minBalanceForInterestCalculation;

    private Boolean enforceMinRequiredBalance;

    private BigDecimal minRequiredBalance;

    private Boolean withdrawalFeeApplicableForTransfer;

    private Boolean allowOverdraft;

    private BigDecimal overdraftLimit;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Instant getSubmittedOnDate() {
        return submittedOnDate;
    }

    public void setSubmittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
    }

    public Instant getRejectedOnDate() {
        return rejectedOnDate;
    }

    public void setRejectedOnDate(Instant rejectedOnDate) {
        this.rejectedOnDate = rejectedOnDate;
    }

    public Instant getApprovedOnDate() {
        return approvedOnDate;
    }

    public void setApprovedOnDate(Instant approvedOnDate) {
        this.approvedOnDate = approvedOnDate;
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

    public BigDecimal getMinBalanceForInterestCalculation() {
        return minBalanceForInterestCalculation;
    }

    public void setMinBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((SavingsAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsAccountDTO{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", status=" + getStatus() +
            ", subStatus=" + getSubStatus() +
            ", accountType=" + getAccountType() +
            ", submittedOnDate='" + getSubmittedOnDate() + "'" +
            ", rejectedOnDate='" + getRejectedOnDate() + "'" +
            ", approvedOnDate='" + getApprovedOnDate() + "'" +
            ", nominalAnnualInterestRate=" + getNominalAnnualInterestRate() +
            ", interestCompoundingPeriodType=" + getInterestCompoundingPeriodType() +
            ", interestPostingPeriodType=" + getInterestPostingPeriodType() +
            ", interestCalculationType=" + getInterestCalculationType() +
            ", interestCalculationDaysInYearType=" + getInterestCalculationDaysInYearType() +
            ", minRequiredOpeningBalance=" + getMinRequiredOpeningBalance() +
            ", lockinPeriodFrequency=" + getLockinPeriodFrequency() +
            ", lockinPeriodFrequencyType=" + getLockinPeriodFrequencyType() +
            ", nominalAnnualInterestRateOverdraft=" + getNominalAnnualInterestRateOverdraft() +
            ", minOverdraftForInterestCalculation=" + getMinOverdraftForInterestCalculation() +
            ", minBalanceForInterestCalculation=" + getMinBalanceForInterestCalculation() +
            ", enforceMinRequiredBalance='" + isEnforceMinRequiredBalance() + "'" +
            ", minRequiredBalance=" + getMinRequiredBalance() +
            ", withdrawalFeeApplicableForTransfer='" + isWithdrawalFeeApplicableForTransfer() + "'" +
            ", allowOverdraft='" + isAllowOverdraft() + "'" +
            ", overdraftLimit=" + getOverdraftLimit() +
            "}";
    }
}

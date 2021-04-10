package com.tetra.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * The SavingsAccount entity.\n@author HamidReza.
 */
@Entity
@Table(name = "m_savings_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SavingsAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sub_status")
    private Integer subStatus;

    @Column(name = "account_type")
    private Integer accountType;

    @Column(name = "submitted_on_date")
    private Instant submittedOnDate;

    @Column(name = "rejected_on_date")
    private Instant rejectedOnDate;

    @Column(name = "approved_on_date")
    private Instant approvedOnDate;

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

    @Column(name = "nominal_annual_interest_rate_overdraft", precision = 21, scale = 2)
    private BigDecimal nominalAnnualInterestRateOverdraft;

    @Column(name = "min_overdraft_for_interest_calculation", precision = 21, scale = 2)
    private BigDecimal minOverdraftForInterestCalculation;

    @Column(name = "min_balance_for_interest_calculation", precision = 21, scale = 2)
    private BigDecimal minBalanceForInterestCalculation;

    @Column(name = "enforce_min_required_balance")
    private Boolean enforceMinRequiredBalance;

    @Column(name = "min_required_balance", precision = 21, scale = 2)
    private BigDecimal minRequiredBalance;

    @Column(name = "withdrawal_fee_applicable_for_transfer")
    private Boolean withdrawalFeeApplicableForTransfer;

    @Column(name = "allow_overdraft")
    private Boolean allowOverdraft;

    @Column(name = "overdraft_limit", precision = 21, scale = 2)
    private BigDecimal overdraftLimit;

    @OneToMany(mappedBy = "savingsAccount")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SavingsAccountTransaction> savingsAccountTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public SavingsAccount accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExternalId() {
        return externalId;
    }

    public SavingsAccount externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getStatus() {
        return status;
    }

    public SavingsAccount status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubStatus() {
        return subStatus;
    }

    public SavingsAccount subStatus(Integer subStatus) {
        this.subStatus = subStatus;
        return this;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public SavingsAccount accountType(Integer accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Instant getSubmittedOnDate() {
        return submittedOnDate;
    }

    public SavingsAccount submittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
        return this;
    }

    public void setSubmittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
    }

    public Instant getRejectedOnDate() {
        return rejectedOnDate;
    }

    public SavingsAccount rejectedOnDate(Instant rejectedOnDate) {
        this.rejectedOnDate = rejectedOnDate;
        return this;
    }

    public void setRejectedOnDate(Instant rejectedOnDate) {
        this.rejectedOnDate = rejectedOnDate;
    }

    public Instant getApprovedOnDate() {
        return approvedOnDate;
    }

    public SavingsAccount approvedOnDate(Instant approvedOnDate) {
        this.approvedOnDate = approvedOnDate;
        return this;
    }

    public void setApprovedOnDate(Instant approvedOnDate) {
        this.approvedOnDate = approvedOnDate;
    }

    public BigDecimal getNominalAnnualInterestRate() {
        return nominalAnnualInterestRate;
    }

    public SavingsAccount nominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
        return this;
    }

    public void setNominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
    }

    public Integer getInterestCompoundingPeriodType() {
        return interestCompoundingPeriodType;
    }

    public SavingsAccount interestCompoundingPeriodType(Integer interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
        return this;
    }

    public void setInterestCompoundingPeriodType(Integer interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
    }

    public Integer getInterestPostingPeriodType() {
        return interestPostingPeriodType;
    }

    public SavingsAccount interestPostingPeriodType(Integer interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
        return this;
    }

    public void setInterestPostingPeriodType(Integer interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
    }

    public Integer getInterestCalculationType() {
        return interestCalculationType;
    }

    public SavingsAccount interestCalculationType(Integer interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
        return this;
    }

    public void setInterestCalculationType(Integer interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
    }

    public Integer getInterestCalculationDaysInYearType() {
        return interestCalculationDaysInYearType;
    }

    public SavingsAccount interestCalculationDaysInYearType(Integer interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
        return this;
    }

    public void setInterestCalculationDaysInYearType(Integer interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
    }

    public BigDecimal getMinRequiredOpeningBalance() {
        return minRequiredOpeningBalance;
    }

    public SavingsAccount minRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
        return this;
    }

    public void setMinRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
    }

    public Integer getLockinPeriodFrequency() {
        return lockinPeriodFrequency;
    }

    public SavingsAccount lockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
        return this;
    }

    public void setLockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
    }

    public Integer getLockinPeriodFrequencyType() {
        return lockinPeriodFrequencyType;
    }

    public SavingsAccount lockinPeriodFrequencyType(Integer lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
        return this;
    }

    public void setLockinPeriodFrequencyType(Integer lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
    }

    public BigDecimal getNominalAnnualInterestRateOverdraft() {
        return nominalAnnualInterestRateOverdraft;
    }

    public SavingsAccount nominalAnnualInterestRateOverdraft(BigDecimal nominalAnnualInterestRateOverdraft) {
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
        return this;
    }

    public void setNominalAnnualInterestRateOverdraft(BigDecimal nominalAnnualInterestRateOverdraft) {
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
    }

    public BigDecimal getMinOverdraftForInterestCalculation() {
        return minOverdraftForInterestCalculation;
    }

    public SavingsAccount minOverdraftForInterestCalculation(BigDecimal minOverdraftForInterestCalculation) {
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
        return this;
    }

    public void setMinOverdraftForInterestCalculation(BigDecimal minOverdraftForInterestCalculation) {
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
    }

    public BigDecimal getMinBalanceForInterestCalculation() {
        return minBalanceForInterestCalculation;
    }

    public SavingsAccount minBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
        return this;
    }

    public void setMinBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
    }

    public Boolean isEnforceMinRequiredBalance() {
        return enforceMinRequiredBalance;
    }

    public SavingsAccount enforceMinRequiredBalance(Boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
        return this;
    }

    public void setEnforceMinRequiredBalance(Boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
    }

    public BigDecimal getMinRequiredBalance() {
        return minRequiredBalance;
    }

    public SavingsAccount minRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
        return this;
    }

    public void setMinRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
    }

    public Boolean isWithdrawalFeeApplicableForTransfer() {
        return withdrawalFeeApplicableForTransfer;
    }

    public SavingsAccount withdrawalFeeApplicableForTransfer(Boolean withdrawalFeeApplicableForTransfer) {
        this.withdrawalFeeApplicableForTransfer = withdrawalFeeApplicableForTransfer;
        return this;
    }

    public void setWithdrawalFeeApplicableForTransfer(Boolean withdrawalFeeApplicableForTransfer) {
        this.withdrawalFeeApplicableForTransfer = withdrawalFeeApplicableForTransfer;
    }

    public Boolean isAllowOverdraft() {
        return allowOverdraft;
    }

    public SavingsAccount allowOverdraft(Boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
        return this;
    }

    public void setAllowOverdraft(Boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public SavingsAccount overdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return this;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public Set<SavingsAccountTransaction> getSavingsAccountTransactions() {
        return savingsAccountTransactions;
    }

    public SavingsAccount savingsAccountTransactions(Set<SavingsAccountTransaction> savingsAccountTransactions) {
        this.savingsAccountTransactions = savingsAccountTransactions;
        return this;
    }

    public SavingsAccount addSavingsAccountTransaction(SavingsAccountTransaction savingsAccountTransaction) {
        this.savingsAccountTransactions.add(savingsAccountTransaction);
        savingsAccountTransaction.setSavingsAccount(this);
        return this;
    }

    public SavingsAccount removeSavingsAccountTransaction(SavingsAccountTransaction savingsAccountTransaction) {
        this.savingsAccountTransactions.remove(savingsAccountTransaction);
        savingsAccountTransaction.setSavingsAccount(null);
        return this;
    }

    public void setSavingsAccountTransactions(Set<SavingsAccountTransaction> savingsAccountTransactions) {
        this.savingsAccountTransactions = savingsAccountTransactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsAccount)) {
            return false;
        }
        return id != null && id.equals(((SavingsAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsAccount{" +
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

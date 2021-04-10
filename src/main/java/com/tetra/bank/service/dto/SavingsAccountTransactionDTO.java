package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.tetra.bank.domain.SavingsAccountTransaction} entity.
 */
@ApiModel(description = "The SavingsAccountTransaction entity.\n@author A true hipster")
public class SavingsAccountTransactionDTO implements Serializable {
    
    private Long id;

    private Integer transactionType;

    private Boolean reversed;

    private Instant dateOf;

    private BigDecimal amount;

    private BigDecimal overdraftAmount;

    private Instant balanceEndDate;

    private Integer balanceNumberOfDays;

    private BigDecimal runningBalance;

    private BigDecimal cumulativeBalance;

    private Instant createdDate;

    private Boolean isManualTransaction;

    private Long releaseIdOfHoldAmountTransaction;


    private Long savingsAccountId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean isReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public Instant getDateOf() {
        return dateOf;
    }

    public void setDateOf(Instant dateOf) {
        this.dateOf = dateOf;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public Instant getBalanceEndDate() {
        return balanceEndDate;
    }

    public void setBalanceEndDate(Instant balanceEndDate) {
        this.balanceEndDate = balanceEndDate;
    }

    public Integer getBalanceNumberOfDays() {
        return balanceNumberOfDays;
    }

    public void setBalanceNumberOfDays(Integer balanceNumberOfDays) {
        this.balanceNumberOfDays = balanceNumberOfDays;
    }

    public BigDecimal getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
    }

    public BigDecimal getCumulativeBalance() {
        return cumulativeBalance;
    }

    public void setCumulativeBalance(BigDecimal cumulativeBalance) {
        this.cumulativeBalance = cumulativeBalance;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean isIsManualTransaction() {
        return isManualTransaction;
    }

    public void setIsManualTransaction(Boolean isManualTransaction) {
        this.isManualTransaction = isManualTransaction;
    }

    public Long getReleaseIdOfHoldAmountTransaction() {
        return releaseIdOfHoldAmountTransaction;
    }

    public void setReleaseIdOfHoldAmountTransaction(Long releaseIdOfHoldAmountTransaction) {
        this.releaseIdOfHoldAmountTransaction = releaseIdOfHoldAmountTransaction;
    }

    public Long getSavingsAccountId() {
        return savingsAccountId;
    }

    public void setSavingsAccountId(Long savingsAccountId) {
        this.savingsAccountId = savingsAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsAccountTransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((SavingsAccountTransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsAccountTransactionDTO{" +
            "id=" + getId() +
            ", transactionType=" + getTransactionType() +
            ", reversed='" + isReversed() + "'" +
            ", dateOf='" + getDateOf() + "'" +
            ", amount=" + getAmount() +
            ", overdraftAmount=" + getOverdraftAmount() +
            ", balanceEndDate='" + getBalanceEndDate() + "'" +
            ", balanceNumberOfDays=" + getBalanceNumberOfDays() +
            ", runningBalance=" + getRunningBalance() +
            ", cumulativeBalance=" + getCumulativeBalance() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", isManualTransaction='" + isIsManualTransaction() + "'" +
            ", releaseIdOfHoldAmountTransaction=" + getReleaseIdOfHoldAmountTransaction() +
            ", savingsAccountId=" + getSavingsAccountId() +
            "}";
    }
}

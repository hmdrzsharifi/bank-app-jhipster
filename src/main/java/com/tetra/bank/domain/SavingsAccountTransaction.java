package com.tetra.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * The SavingsAccountTransaction entity.\n@author A true hipster
 */
@Entity
@Table(name = "m_savings_account_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SavingsAccountTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "reversed")
    private Boolean reversed;

    @Column(name = "date_of")
    private Instant dateOf;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "overdraft_amount", precision = 21, scale = 2)
    private BigDecimal overdraftAmount;

    @Column(name = "balance_end_date")
    private Instant balanceEndDate;

    @Column(name = "balance_number_of_days")
    private Integer balanceNumberOfDays;

    @Column(name = "running_balance", precision = 21, scale = 2)
    private BigDecimal runningBalance;

    @Column(name = "cumulative_balance", precision = 21, scale = 2)
    private BigDecimal cumulativeBalance;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "is_manual_transaction")
    private Boolean isManualTransaction;

    @Column(name = "release_id_of_hold_amount_transaction")
    private Long releaseIdOfHoldAmountTransaction;

    @ManyToOne
    @JsonIgnoreProperties(value = "savingsAccountTransactions", allowSetters = true)
    private SavingsAccount savingsAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public SavingsAccountTransaction transactionType(Integer transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean isReversed() {
        return reversed;
    }

    public SavingsAccountTransaction reversed(Boolean reversed) {
        this.reversed = reversed;
        return this;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public Instant getDateOf() {
        return dateOf;
    }

    public SavingsAccountTransaction dateOf(Instant dateOf) {
        this.dateOf = dateOf;
        return this;
    }

    public void setDateOf(Instant dateOf) {
        this.dateOf = dateOf;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SavingsAccountTransaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOverdraftAmount() {
        return overdraftAmount;
    }

    public SavingsAccountTransaction overdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
        return this;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    public Instant getBalanceEndDate() {
        return balanceEndDate;
    }

    public SavingsAccountTransaction balanceEndDate(Instant balanceEndDate) {
        this.balanceEndDate = balanceEndDate;
        return this;
    }

    public void setBalanceEndDate(Instant balanceEndDate) {
        this.balanceEndDate = balanceEndDate;
    }

    public Integer getBalanceNumberOfDays() {
        return balanceNumberOfDays;
    }

    public SavingsAccountTransaction balanceNumberOfDays(Integer balanceNumberOfDays) {
        this.balanceNumberOfDays = balanceNumberOfDays;
        return this;
    }

    public void setBalanceNumberOfDays(Integer balanceNumberOfDays) {
        this.balanceNumberOfDays = balanceNumberOfDays;
    }

    public BigDecimal getRunningBalance() {
        return runningBalance;
    }

    public SavingsAccountTransaction runningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
        return this;
    }

    public void setRunningBalance(BigDecimal runningBalance) {
        this.runningBalance = runningBalance;
    }

    public BigDecimal getCumulativeBalance() {
        return cumulativeBalance;
    }

    public SavingsAccountTransaction cumulativeBalance(BigDecimal cumulativeBalance) {
        this.cumulativeBalance = cumulativeBalance;
        return this;
    }

    public void setCumulativeBalance(BigDecimal cumulativeBalance) {
        this.cumulativeBalance = cumulativeBalance;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public SavingsAccountTransaction createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean isIsManualTransaction() {
        return isManualTransaction;
    }

    public SavingsAccountTransaction isManualTransaction(Boolean isManualTransaction) {
        this.isManualTransaction = isManualTransaction;
        return this;
    }

    public void setIsManualTransaction(Boolean isManualTransaction) {
        this.isManualTransaction = isManualTransaction;
    }

    public Long getReleaseIdOfHoldAmountTransaction() {
        return releaseIdOfHoldAmountTransaction;
    }

    public SavingsAccountTransaction releaseIdOfHoldAmountTransaction(Long releaseIdOfHoldAmountTransaction) {
        this.releaseIdOfHoldAmountTransaction = releaseIdOfHoldAmountTransaction;
        return this;
    }

    public void setReleaseIdOfHoldAmountTransaction(Long releaseIdOfHoldAmountTransaction) {
        this.releaseIdOfHoldAmountTransaction = releaseIdOfHoldAmountTransaction;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public SavingsAccountTransaction savingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
        return this;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SavingsAccountTransaction)) {
            return false;
        }
        return id != null && id.equals(((SavingsAccountTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SavingsAccountTransaction{" +
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
            "}";
    }
}

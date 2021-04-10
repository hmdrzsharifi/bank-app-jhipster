package com.tetra.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * The Client entity.\n@author HamidReza.
 */
@Entity
@Table(name = "m_client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "status")
    private Integer status;

    @Column(name = "activation_date")
    private Instant activationDate;

    @Size(max = 50)
    @Column(name = "firstname", length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(name = "lastname", length = 50)
    private String lastname;

    @Size(max = 50)
    @Column(name = "middlename", length = 50)
    private String middlename;

    @Size(max = 100)
    @Column(name = "fullname", length = 100)
    private String fullname;

    @Size(max = 100)
    @Column(name = "display_name", length = 100)
    private String displayName;

    @Size(max = 50)
    @Column(name = "mobile_no", length = 50, unique = true)
    private String mobileNo;

    @Size(max = 50)
    @Column(name = "email_address", length = 50, unique = true)
    private String emailAddress;

    @NotNull
    @Column(name = "is_staff", nullable = false)
    private Boolean isStaff;

    @Size(max = 100)
    @Column(name = "external_id", length = 100, unique = true)
    private String externalId;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "closure_date")
    private Instant closureDate;

    @Column(name = "rejection_date")
    private Instant rejectionDate;

    @Column(name = "reactivate_date")
    private Instant reactivateDate;

    @Column(name = "submitted_on_date")
    private Instant submittedOnDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Image image;

    @ManyToOne
    @JsonIgnoreProperties(value = "clients", allowSetters = true)
    private Staff staff;

    @ManyToOne
    @JsonIgnoreProperties(value = "clients", allowSetters = true)
    private Office office;

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

    public Client accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public Client status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getActivationDate() {
        return activationDate;
    }

    public Client activationDate(Instant activationDate) {
        this.activationDate = activationDate;
        return this;
    }

    public void setActivationDate(Instant activationDate) {
        this.activationDate = activationDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public Client firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Client lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Client middlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFullname() {
        return fullname;
    }

    public Client fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Client displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Client mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Client emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isIsStaff() {
        return isStaff;
    }

    public Client isStaff(Boolean isStaff) {
        this.isStaff = isStaff;
        return this;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getExternalId() {
        return externalId;
    }

    public Client externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public Client dateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getClosureDate() {
        return closureDate;
    }

    public Client closureDate(Instant closureDate) {
        this.closureDate = closureDate;
        return this;
    }

    public void setClosureDate(Instant closureDate) {
        this.closureDate = closureDate;
    }

    public Instant getRejectionDate() {
        return rejectionDate;
    }

    public Client rejectionDate(Instant rejectionDate) {
        this.rejectionDate = rejectionDate;
        return this;
    }

    public void setRejectionDate(Instant rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public Instant getReactivateDate() {
        return reactivateDate;
    }

    public Client reactivateDate(Instant reactivateDate) {
        this.reactivateDate = reactivateDate;
        return this;
    }

    public void setReactivateDate(Instant reactivateDate) {
        this.reactivateDate = reactivateDate;
    }

    public Instant getSubmittedOnDate() {
        return submittedOnDate;
    }

    public Client submittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
        return this;
    }

    public void setSubmittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
    }

    public Image getImage() {
        return image;
    }

    public Client image(Image image) {
        this.image = image;
        return this;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Staff getStaff() {
        return staff;
    }

    public Client staff(Staff staff) {
        this.staff = staff;
        return this;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Office getOffice() {
        return office;
    }

    public Client office(Office office) {
        this.office = office;
        return this;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", status=" + getStatus() +
            ", activationDate='" + getActivationDate() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", middlename='" + getMiddlename() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", isStaff='" + isIsStaff() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", closureDate='" + getClosureDate() + "'" +
            ", rejectionDate='" + getRejectionDate() + "'" +
            ", reactivateDate='" + getReactivateDate() + "'" +
            ", submittedOnDate='" + getSubmittedOnDate() + "'" +
            "}";
    }
}

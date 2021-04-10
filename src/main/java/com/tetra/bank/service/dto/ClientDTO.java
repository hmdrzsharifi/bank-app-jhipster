package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tetra.bank.domain.Client} entity.
 */
@ApiModel(description = "The Client entity.\n@author HamidReza.")
public class ClientDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String accountNumber;

    private Integer status;

    private Instant activationDate;

    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String lastname;

    @Size(max = 50)
    private String middlename;

    @Size(max = 100)
    private String fullname;

    @Size(max = 100)
    private String displayName;

    @Size(max = 50)
    private String mobileNo;

    @Size(max = 50)
    private String emailAddress;

    @NotNull
    private Boolean isStaff;

    @Size(max = 100)
    private String externalId;

    private Instant dateOfBirth;

    private Instant closureDate;

    private Instant rejectionDate;

    private Instant reactivateDate;

    private Instant submittedOnDate;


    private Long imageId;

    private Long staffId;

    private Long officeId;
    
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Instant activationDate) {
        this.activationDate = activationDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Instant closureDate) {
        this.closureDate = closureDate;
    }

    public Instant getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(Instant rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public Instant getReactivateDate() {
        return reactivateDate;
    }

    public void setReactivateDate(Instant reactivateDate) {
        this.reactivateDate = reactivateDate;
    }

    public Instant getSubmittedOnDate() {
        return submittedOnDate;
    }

    public void setSubmittedOnDate(Instant submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
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
            ", imageId=" + getImageId() +
            ", staffId=" + getStaffId() +
            ", officeId=" + getOfficeId() +
            "}";
    }
}

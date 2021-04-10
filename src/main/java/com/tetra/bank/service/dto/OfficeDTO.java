package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tetra.bank.domain.Office} entity.
 */
@ApiModel(description = "The Office entity.\n@author HamidReza.")
public class OfficeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Instant openingDate;

    @Size(max = 100)
    private String externalId;


    private Long parentId;
    
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

    public Instant getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Instant openingDate) {
        this.openingDate = openingDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long officeId) {
        this.parentId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfficeDTO)) {
            return false;
        }

        return id != null && id.equals(((OfficeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfficeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", openingDate='" + getOpeningDate() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", parentId=" + getParentId() +
            "}";
    }
}

package com.tetra.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tetra.bank.domain.PaymentDetails} entity.
 */
@ApiModel(description = "The PaymentDetail entity.\n@author A true hipster")
public class PaymentDetailsDTO implements Serializable {
    
    private Long id;

    /**
     * fieldName
     */
    @ApiModelProperty(value = "fieldName")
    private String fieldName;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDetailsDTO{" +
            "id=" + getId() +
            ", fieldName='" + getFieldName() + "'" +
            "}";
    }
}

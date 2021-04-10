package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.PaymentDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentDetails} and its DTO {@link PaymentDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentDetailsMapper extends EntityMapper<PaymentDetailsDTO, PaymentDetails> {



    default PaymentDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(id);
        return paymentDetails;
    }
}

package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.SavingsAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SavingsAccount} and its DTO {@link SavingsAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SavingsAccountMapper extends EntityMapper<SavingsAccountDTO, SavingsAccount> {


    @Mapping(target = "savingsAccountTransactions", ignore = true)
    @Mapping(target = "removeSavingsAccountTransaction", ignore = true)
    SavingsAccount toEntity(SavingsAccountDTO savingsAccountDTO);

    default SavingsAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setId(id);
        return savingsAccount;
    }
}

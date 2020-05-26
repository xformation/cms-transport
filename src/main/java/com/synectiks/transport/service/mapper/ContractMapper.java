package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.ContractDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContractMapper extends EntityMapper<ContractDTO, Contract> {



    default Contract fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(id);
        return contract;
    }
}

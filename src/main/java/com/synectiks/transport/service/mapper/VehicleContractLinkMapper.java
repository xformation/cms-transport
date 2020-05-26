package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.VehicleContractLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VehicleContractLink} and its DTO {@link VehicleContractLinkDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class, ContractMapper.class})
public interface VehicleContractLinkMapper extends EntityMapper<VehicleContractLinkDTO, VehicleContractLink> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "contract.id", target = "contractId")
    VehicleContractLinkDTO toDto(VehicleContractLink vehicleContractLink);

    @Mapping(source = "vehicleId", target = "vehicle")
    @Mapping(source = "contractId", target = "contract")
    VehicleContractLink toEntity(VehicleContractLinkDTO vehicleContractLinkDTO);

    default VehicleContractLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        vehicleContractLink.setId(id);
        return vehicleContractLink;
    }
}

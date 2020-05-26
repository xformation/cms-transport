package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.VehicleDriverLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VehicleDriverLink} and its DTO {@link VehicleDriverLinkDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface VehicleDriverLinkMapper extends EntityMapper<VehicleDriverLinkDTO, VehicleDriverLink> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    VehicleDriverLinkDTO toDto(VehicleDriverLink vehicleDriverLink);

    @Mapping(source = "vehicleId", target = "vehicle")
    VehicleDriverLink toEntity(VehicleDriverLinkDTO vehicleDriverLinkDTO);

    default VehicleDriverLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink();
        vehicleDriverLink.setId(id);
        return vehicleDriverLink;
    }
}

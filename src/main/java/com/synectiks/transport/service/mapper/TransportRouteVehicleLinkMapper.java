package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.TransportRouteVehicleLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransportRouteVehicleLink} and its DTO {@link TransportRouteVehicleLinkDTO}.
 */
@Mapper(componentModel = "spring", uses = {TransportRouteMapper.class, VehicleMapper.class})
public interface TransportRouteVehicleLinkMapper extends EntityMapper<TransportRouteVehicleLinkDTO, TransportRouteVehicleLink> {

    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    @Mapping(source = "vehicle.id", target = "vehicleId")
    TransportRouteVehicleLinkDTO toDto(TransportRouteVehicleLink transportRouteVehicleLink);

    @Mapping(source = "transportRouteId", target = "transportRoute")
    @Mapping(source = "vehicleId", target = "vehicle")
    TransportRouteVehicleLink toEntity(TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO);

    default TransportRouteVehicleLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
        transportRouteVehicleLink.setId(id);
        return transportRouteVehicleLink;
    }
}

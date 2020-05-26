package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.TransportRouteStopageLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransportRouteStopageLink} and its DTO {@link TransportRouteStopageLinkDTO}.
 */
@Mapper(componentModel = "spring", uses = {TransportRouteMapper.class, StopageMapper.class})
public interface TransportRouteStopageLinkMapper extends EntityMapper<TransportRouteStopageLinkDTO, TransportRouteStopageLink> {

    @Mapping(source = "transportRoute.id", target = "transportRouteId")
    @Mapping(source = "stopage.id", target = "stopageId")
    TransportRouteStopageLinkDTO toDto(TransportRouteStopageLink transportRouteStopageLink);

    @Mapping(source = "transportRouteId", target = "transportRoute")
    @Mapping(source = "stopageId", target = "stopage")
    TransportRouteStopageLink toEntity(TransportRouteStopageLinkDTO transportRouteStopageLinkDTO);

    default TransportRouteStopageLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        transportRouteStopageLink.setId(id);
        return transportRouteStopageLink;
    }
}

package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.TransportRouteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransportRoute} and its DTO {@link TransportRouteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransportRouteMapper extends EntityMapper<TransportRouteDTO, TransportRoute> {



    default TransportRoute fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransportRoute transportRoute = new TransportRoute();
        transportRoute.setId(id);
        return transportRoute;
    }
}

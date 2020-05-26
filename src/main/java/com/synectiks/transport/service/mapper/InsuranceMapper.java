package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.InsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Insurance} and its DTO {@link InsuranceDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface InsuranceMapper extends EntityMapper<InsuranceDTO, Insurance> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    InsuranceDTO toDto(Insurance insurance);

    @Mapping(source = "vehicleId", target = "vehicle")
    Insurance toEntity(InsuranceDTO insuranceDTO);

    default Insurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Insurance insurance = new Insurance();
        insurance.setId(id);
        return insurance;
    }
}

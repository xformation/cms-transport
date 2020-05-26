package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.StopageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stopage} and its DTO {@link StopageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StopageMapper extends EntityMapper<StopageDTO, Stopage> {



    default Stopage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stopage stopage = new Stopage();
        stopage.setId(id);
        return stopage;
    }
}

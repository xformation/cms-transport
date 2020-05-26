package com.synectiks.transport.service.mapper;


import com.synectiks.transport.domain.*;
import com.synectiks.transport.service.dto.DocumentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Documents} and its DTO {@link DocumentsDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class, ContractMapper.class})
public interface DocumentsMapper extends EntityMapper<DocumentsDTO, Documents> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "contract.id", target = "contractId")
    DocumentsDTO toDto(Documents documents);

    @Mapping(source = "vehicleId", target = "vehicle")
    @Mapping(source = "contractId", target = "contract")
    Documents toEntity(DocumentsDTO documentsDTO);

    default Documents fromId(Long id) {
        if (id == null) {
            return null;
        }
        Documents documents = new Documents();
        documents.setId(id);
        return documents;
    }
}

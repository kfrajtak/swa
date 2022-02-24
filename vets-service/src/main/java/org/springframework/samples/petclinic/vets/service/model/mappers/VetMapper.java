package org.springframework.samples.petclinic.vets.service.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.samples.petclinic.vets.service.api.model.VetDto;
import org.springframework.samples.petclinic.vets.service.model.Vet;

@Mapper
public interface VetMapper {
    public static VetMapper INSTANCE = Mappers.getMapper(VetMapper.class);

    VetDto mapTo(Vet vet);
    Vet mapTo(VetDto vetDto);
}
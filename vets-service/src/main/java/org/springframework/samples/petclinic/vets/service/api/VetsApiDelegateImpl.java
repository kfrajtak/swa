package org.springframework.samples.petclinic.vets.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vets.service.api.model.SpecialtyDto;
import org.springframework.samples.petclinic.vets.service.api.model.VetDto;
import org.springframework.samples.petclinic.vets.service.model.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetsApiDelegateImpl implements VetsApiDelegate {

    private final VetRepository vetRepository;

    public VetsApiDelegateImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public ResponseEntity<List<VetDto>> getAllVets() {
        return ResponseEntity.ok(
                vetRepository.findAll()
                        .stream().map(e -> {
                            VetDto dto = new VetDto();
                            dto.name(e.getLastName() + " " + e.getFirstName());
                            dto.id(Long.valueOf(e.getId()));
                            dto.specialties(e.getSpecialties().stream().map(s -> {
                                SpecialtyDto specialtyDto = new SpecialtyDto();
                                specialtyDto.id(s.getId());
                                specialtyDto.name(s.getName());
                                return specialtyDto;
                            }).collect(Collectors.toList()));
                            return dto;
                        })
                        .collect(Collectors.toList()));
    }
}

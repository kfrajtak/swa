package org.springframework.samples.petclinic.vets.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vets.service.api.model.VetDto;
import org.springframework.samples.petclinic.vets.service.model.VetRepository;
import org.springframework.samples.petclinic.vets.service.model.mappers.VetMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetsApiDelegateImpl implements org.springframework.samples.petclinic.vets.service.api.VetsApiDelegate {

    private final VetRepository vetRepository;

    public VetsApiDelegateImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public ResponseEntity<List<VetDto>> getAllVets() {
        return ResponseEntity.ok(
            vetRepository.findAll()
                .stream().map(VetMapper.INSTANCE::mapTo)
                .collect(Collectors.toList()));
    }
}

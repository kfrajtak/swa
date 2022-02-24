package org.springframework.samples.petclinic.vets.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vets.service.api.model.VetDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetsApiDelegateImpl implements VetsApiDelegate {

    @Override
    public ResponseEntity<List<VetDto>> getAllVets() {
        List<VetDto> vets = new ArrayList<>();
        vets.add(new VetDto().id(1l).firstName("X").lastName("Y"));
        vets.add(new VetDto());
        vets.add(new VetDto().id(3l).firstName("A").lastName("D"));
        return ResponseEntity.ok(vets);
    }
}
/*public class VetsApiDelegateImpl implements VetsApiDelegate {

    private final VetRepository vetRepository;

    public VetsApiDelegateImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public ResponseEntity<List<VetDto>> listAllVets() {
        return ResponseEntity.ok(
            vetRepository.findAll()
                .stream().map(VetMapper.INSTANCE::mapTo)
                .collect(Collectors.toList()));
    }
}*/

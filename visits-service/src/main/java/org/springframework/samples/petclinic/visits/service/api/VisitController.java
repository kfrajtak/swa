package org.springframework.samples.petclinic.visits.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VisitController {
    private final VetsServiceApiClient vetsServiceApiClient;

    public VisitController(VetsServiceApiClient vetsServiceApiClient) {
        this.vetsServiceApiClient = vetsServiceApiClient;
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/number-of-vets",
        produces = { "application/json" }
    )
    public ResponseEntity<Integer> getNumberOfVets(){
        return ResponseEntity.ok(this.vetsServiceApiClient.getAllVets().getBody().size());
    }
}

package org.springframework.samples.petclinic.visits.service.api;

import org.springframework.stereotype.Controller;

@Controller
public class VisitController {
    private final VetsServiceApiClient vetsServiceApiClient;

    public VisitController(VetsServiceApiClient vetsServiceApiClient) {
        this.vetsServiceApiClient = vetsServiceApiClient;
    }

    public int getNumberOfVets(){
        return this.vetsServiceApiClient.getAllVets().getBody().size();
    }
}

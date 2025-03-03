package org.springframework.samples.petclinic.visits.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class VisitController {
    private final VetsServiceApiClient vetsServiceApiClient;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/info",
            produces = { "application/json" }
    )
    public ResponseEntity<String> handler() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("vets-service");
        ServiceInstance instance = instances.get(0);
        String hostname = instance.getHost();
        int port = instance.getPort();

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://" + hostname + ":" + port + "/vets";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        return response;
    }
}

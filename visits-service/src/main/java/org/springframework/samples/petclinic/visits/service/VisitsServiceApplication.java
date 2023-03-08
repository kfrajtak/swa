package org.springframework.samples.petclinic.visits.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.visits.service.api.VetsServiceApiClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VisitsServiceApplication {
    Logger logger = LoggerFactory.getLogger(VisitsServiceApplication.class);

    @Autowired
    private VetsServiceApiClient vetsServiceApiClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(VisitsServiceApplication.class, args);
    }

    @PostConstruct
    public void start() {
        logger.info("Starting ...");
        // get the information about the service programmatically
        try {
            List<ServiceInstance> instances = this.discoveryClient.getInstances("vets-service");
            List<String> uris = instances.stream().map(i -> i.getUri().toString()).collect(Collectors.toList());
            logger.info("vets-service running at {}", String.join(", ", uris));

            List<String> services = this.discoveryClient.getServices();
            logger.info("available services: {}", String.join(", ", services));

            logger.info(vetsServiceApiClient.getAllVets().getBody().size() + "");
        }
        catch (Throwable t) {
            logger.warn("An error has occurred in vets-service discovery", t);
        }
	}
}

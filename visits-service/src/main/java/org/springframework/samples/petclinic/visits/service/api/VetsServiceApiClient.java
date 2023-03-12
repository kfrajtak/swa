package org.springframework.samples.petclinic.visits.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.visits.service.api.model.VetDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@FeignClient(
    name = "vets-service",
    fallback = VetsServiceApiClient.VetsServiceApiClientFallback.class)
public interface VetsServiceApiClient extends VetsApi {
    Logger logger = LoggerFactory.getLogger(VetsServiceApiClient.class);

    @Component
    class VetsServiceApiClientFallback implements VetsServiceApiClient {
        @Override
        public ResponseEntity<List<VetDto>> getAllVets() {
            logger.info("fallback to get empty list of vets");
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}

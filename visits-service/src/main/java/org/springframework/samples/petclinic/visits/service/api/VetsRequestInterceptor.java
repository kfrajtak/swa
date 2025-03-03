package org.springframework.samples.petclinic.visits.service.api;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class VetsRequestInterceptor {
    Logger logger = LoggerFactory.getLogger(VetsRequestInterceptor.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String method = requestTemplate.method();
            String url = requestTemplate.feignTarget().url();
            String path = requestTemplate.path();

            if (url.contains("vets-service")) {
                List<ServiceInstance> instances = this.discoveryClient.getInstances("vets-service");
                if (instances.isEmpty()) {
                    return;
                }

                List<String> uris = instances.stream().map(i -> i.getUri().toString()).collect(Collectors.toList());

                //logger.info("Replacing vets-service with " + uris.get(0));
                //requestTemplate.uri(uris.get(0));
            }

            logger.info("method: {}", method);
            logger.info("url: {}", path);
            logger.info("full url: {}{}", url, path);
        };
    }
}
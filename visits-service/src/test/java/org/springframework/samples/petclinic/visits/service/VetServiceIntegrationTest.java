package org.springframework.samples.petclinic.visits.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.visits.service.api.VetsServiceApiClient;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {"spring.cloud.discovery.client.simple.instances.vets-service[0].uri=http://localhost:9561"})
@EnableConfigurationProperties
class VetServiceIntegrationTest {
    @Autowired
    private VetsServiceApiClient vetsServiceApiClient;

    @RegisterExtension
    static WireMockExtension ext = WireMockExtension.newInstance()
        .options(wireMockConfig().port(9561))
        .configureStaticDsl(true)
        .build();

    @BeforeEach
    void setUp() {
        // https://www.baeldung.com/spring-cloud-feign-integration-tests
        stubFor(WireMock.get(WireMock.urlEqualTo("/vets"))
            .willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("[]")));
    }

    @Test
    public void whenGetVets_thenBooksShouldBeReturned() {
        assertTrue(vetsServiceApiClient.getAllVets().getBody().isEmpty());
    }
}
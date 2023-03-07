package org.springframework.samples.petclinic.visits.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

@EnableConfigurationProperties
@SpringBootTest(
    classes = VisitsServiceApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"spring.cloud.discovery.client.simple.instances.vets-service[0].uri=http://localhost:9561"})
public class VisitsServiceApplicationTests {
    @LocalServerPort
    private int port;

    @RegisterExtension
    static WireMockExtension ext = WireMockExtension.newInstance()
        .options(wireMockConfig().port(9561))
        .configureStaticDsl(true)
        .build();

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void givenUrl_whenGetRequest_thenOK() {
        // setup
        stubFor(WireMock.get(WireMock.urlEqualTo("/vets"))
            .willReturn(WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("[{},{}]")));

        // act & assert
        get("/number-of-vets").then().assertThat()
            .statusCode(200)
            .and()
            .body(equalTo("2"));
    }
}

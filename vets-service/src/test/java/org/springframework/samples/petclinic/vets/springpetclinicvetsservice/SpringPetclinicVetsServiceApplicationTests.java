package org.springframework.samples.petclinic.vets.springpetclinicvetsservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.samples.petclinic.vets.service.SpringPetclinicVetsServiceApplication;

import static io.restassured.RestAssured.get;

@SpringBootTest(classes = SpringPetclinicVetsServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringPetclinicVetsServiceApplicationTests {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void givenUrl_whenGetRequest_thenOK() {
        get("/vets").then().assertThat().statusCode(200);
    }
}

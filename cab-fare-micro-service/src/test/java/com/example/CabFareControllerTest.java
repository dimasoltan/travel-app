package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CabFareControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CabFareService cabFareService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFare_Success() {
        String from = "A";
        String to = "B";
        String cabType = "innova";
        Double expectedFare = 950.0;

        when(cabFareService.getFare(from, to, cabType)).thenReturn(expectedFare);

        ResponseEntity<Double> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/fare?from=" + from + "&to=" + to + "&typeofcab=" + cabType,
                Double.class
        );

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedFare);
    }

    @Test
    public void testGetFare_NotFound() {
        String from = "A";
        String to = "The Moon";
        String cabType = "innova";

        when(cabFareService.getFare(from, to, cabType)).thenReturn(null);

        ResponseEntity<Double> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/fare?from=" + from + "&to=" + to + "&typeofcab=" + cabType,
                Double.class
        );

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNull();
    }
}
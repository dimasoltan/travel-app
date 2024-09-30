package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class CabFareServiceTest {

    @Mock
    private CabFareRepository cabFareRepository;

    @InjectMocks
    private CabFareService cabFareService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFare_Success() {
        String from = "A";
        String to = "B";
        String type = "innova";
        Double fare = 950.0;

        when(cabFareRepository.findFare(from, to, type)).thenReturn(fare);

        Double result = cabFareService.getFare(from, to, type);
        assertEquals(fare, result);
    }

    @Test
    public void testGetFare_NotFound() {
        String from = "A";
        String to = "The Moon";
        String type = "innova";

        when(cabFareRepository.findFare(from, to, type)).thenReturn(null);

        Double result = cabFareService.getFare(from, to, type);
        assertNull(result);
    }
}
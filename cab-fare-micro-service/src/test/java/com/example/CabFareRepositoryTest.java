package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class CabFareRepositoryTest {

    @Autowired
    private CabFareRepository cabFareRepository;

    @BeforeEach
    public void setUp() {
        cabFareRepository.deleteAll();
    }

    @Test
    public void testGetFare_Success() {
        CabFare entity = new CabFare();
        entity.setAmount(950.0);
        entity.setFromLocation("A");
        entity.setToLocation("B");
        entity.setTypeOfCab("innova");
        cabFareRepository.save(entity);

        Double fare = cabFareRepository.findFare("A", "B", "innova");
        assertEquals(950.0, fare);
    }

    @Test
    public void testGetFare_NotFound() {
        Double fare = cabFareRepository.findFare("A", "B", "innova");
        assertNull(fare);
    }
}

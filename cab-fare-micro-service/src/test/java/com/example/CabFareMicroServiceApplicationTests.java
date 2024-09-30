package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CabFareMicroServiceApplicationTests {

    @Autowired
    private CabFareController cabFareController;

    @Test
    void contextLoads() {
        Assertions.assertThat(cabFareController).isNotNull();
    }

}

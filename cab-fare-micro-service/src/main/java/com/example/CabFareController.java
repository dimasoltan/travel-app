package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fare")
public class CabFareController {

    @Autowired
    private CabFareService cabFareService;

    @GetMapping
    public ResponseEntity<Double> getFare(@RequestParam String from, @RequestParam String to, @RequestParam String typeofcab) {
        Double fare = cabFareService.getFare(from, to, typeofcab);
        if (fare != null) {
            return ResponseEntity.ok(fare);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

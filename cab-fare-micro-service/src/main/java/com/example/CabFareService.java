package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabFareService {

    @Autowired
    private CabFareRepository cabFareRepository;

    public Double getFare(String from, String to, String type) {
        return cabFareRepository.findFare(from, to, type);
    }
}
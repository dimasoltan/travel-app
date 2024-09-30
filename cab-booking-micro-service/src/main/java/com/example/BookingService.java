package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Double getFare(String from, String to, String type) {
        String fareUrl = "http://localhost:8282/api/fare?from=" + from + "&to=" + to + "&typeofcab=" + type;
        try {
            return restTemplate.getForObject(fareUrl, Double.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean bookCab(Booking booking) {
        String fromLocation = booking.getFromLocation();
        String toLocation = booking.getToLocation();
        String typeOfCab = booking.getTypeOfCab();
        Double fare = getFare(fromLocation, toLocation, typeOfCab);
        if (fare != null) {
            bookingRepository.save(booking);
            return true;
        } else {
            return false;
        }
    }

}
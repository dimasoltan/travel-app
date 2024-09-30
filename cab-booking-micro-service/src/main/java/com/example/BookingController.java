package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/bookcab")
    public String showBookCabForm() {
        return "bookcab";
    }

    @GetMapping("/cabfare")
    public String showCabFareForm() {
        return "cabfare";
    }

    @PostMapping("/book")
    public String bookCab(@ModelAttribute Booking booking, Model model) {
        boolean result = bookingService.bookCab(booking);
        String fromLocation = booking.getFromLocation();
        String toLocation = booking.getToLocation();
        String typeOfCab = booking.getTypeOfCab();
        if (result) {
            model.addAttribute("message", String.format("%s Cab Booked from %s to %s ", typeOfCab, fromLocation, toLocation));
        } else {
            model.addAttribute("error", "No cab fare found!");
        }
        return "bookcab";
    }

    @PostMapping("/cabfare")
    public String getCabFare(@RequestParam("fromLocation") String fromLocation,
                             @RequestParam("toLocation") String toLocation,
                             @RequestParam("typeOfCab") String typeOfCab,
                             Model model) {

        Double fare = bookingService.getFare(fromLocation, toLocation, typeOfCab);

        model.addAttribute("fare", fare);
        model.addAttribute("showFare", true);
        return "cabfare";
    }

}
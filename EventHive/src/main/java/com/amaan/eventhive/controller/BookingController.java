package com.amaan.eventhive.controller;

import com.amaan.eventhive.dto.BookingRequestDTO;
import com.amaan.eventhive.entity.Booking;
import com.amaan.eventhive.service.BookingService;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(
            Authentication authentication,
            @RequestBody @Valid BookingRequestDTO request) {

        String email = authentication.getName();

        return bookingService.createBooking(email, request);
    }

    @GetMapping("/my")
    public List<Booking> getMyBookings(
            Authentication authentication) {

        String email = authentication.getName();

        return bookingService.getBookingsByEmail(email);
    }

    @PutMapping("/{bookingId}/cancel")
    public Booking cancelBooking(
            @PathVariable Long bookingId,
            Authentication authentication) {

        String email = authentication.getName();

        return bookingService.cancelBooking(bookingId, email);
    }
}
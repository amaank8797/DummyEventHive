package com.amaan.eventhive.controller;

import com.amaan.eventhive.dto.BookingRequestDTO;
import com.amaan.eventhive.entity.Booking;
import com.amaan.eventhive.service.BookingService;
import javax.validation.Valid;
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
            @RequestParam Long userId,
            @RequestBody @Valid BookingRequestDTO request) {

        return bookingService.createBooking(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(
            @PathVariable Long userId) {

        return bookingService.getBookingsByUserId(userId);
    }

    @PutMapping("/{bookingId}/cancel")
    public Booking cancelBooking(
            @PathVariable Long bookingId,
            @RequestParam Long userId) {

        return bookingService.cancelBooking(bookingId, userId);
    }
}
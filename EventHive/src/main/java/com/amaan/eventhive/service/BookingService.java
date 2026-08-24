package com.amaan.eventhive.service;


import com.amaan.eventhive.dto.BookingRequestDTO;
import com.amaan.eventhive.entity.Booking;
import com.amaan.eventhive.entity.TicketType;
import com.amaan.eventhive.entity.User;
import com.amaan.eventhive.repository.BookingRepository;
import com.amaan.eventhive.repository.TicketTypeRepository;
import com.amaan.eventhive.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          TicketTypeRepository ticketTypeRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, BookingRequestDTO request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );

        TicketType ticketType = ticketTypeRepository
                .findById(request.getTicketTypeId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Ticket type not found"
                        )
                );

        int availableTickets =
                ticketType.getTotalQuantity() - ticketType.getQuantitySold();

        if (request.getQuantity() > availableTickets) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Not enough tickets available"
            );
        }

        BigDecimal totalAmount = ticketType.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setTicketType(ticketType);
        booking.setQuantity(request.getQuantity());
        booking.setTotalAmount(totalAmount);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        ticketType.setQuantitySold(
                ticketType.getQuantitySold() + request.getQuantity()
        );

        ticketTypeRepository.save(ticketType);

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    @Transactional
    public Booking cancelBooking(Long bookingId, Long userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElse(null);

        if (booking == null) {
            return null;
        }

        if ("CANCELLED".equals(booking.getStatus())) {
            return booking;
        }

        if (!booking.getUser().getId().equals(userId)) {
            return null;
        }

        if (booking.getTicketType()
                .getEvent()
                .getEventDate()
                .isBefore(java.time.LocalDateTime.now())) {
            return null;
        }

        TicketType ticketType = booking.getTicketType();

        ticketType.setQuantitySold(
                ticketType.getQuantitySold() - booking.getQuantity()
        );

        booking.setStatus("CANCELLED");

        ticketTypeRepository.save(ticketType);

        return bookingRepository.save(booking);
    }
}

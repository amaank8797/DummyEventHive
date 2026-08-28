package com.amaan.eventhive.service;

import com.amaan.eventhive.dto.BookingRequestDTO;
import com.amaan.eventhive.entity.Booking;
import com.amaan.eventhive.entity.TicketType;
import com.amaan.eventhive.entity.User;
import com.amaan.eventhive.exception.BusinessException;
import com.amaan.eventhive.exception.ForbiddenException;
import com.amaan.eventhive.exception.ResourceNotFoundException;
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

    public BookingService(
            BookingRepository bookingRepository,
            TicketTypeRepository ticketTypeRepository,
            UserRepository userRepository) {

        this.bookingRepository = bookingRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Booking createBooking(
            String email,
            BookingRequestDTO request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        TicketType ticketType = ticketTypeRepository
                .findById(request.getTicketTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket type not found"
                        )
                );

        int availableTickets =
                ticketType.getTotalQuantity()
                        - ticketType.getQuantitySold();

        if (request.getQuantity() > availableTickets) {

            throw new BusinessException(
                    "Not enough tickets available"
            );
        }

        BigDecimal totalAmount =
                ticketType.getPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        request.getQuantity()
                                )
                        );

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setTicketType(ticketType);
        booking.setQuantity(request.getQuantity());
        booking.setTotalAmount(totalAmount);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        ticketType.setQuantitySold(
                ticketType.getQuantitySold()
                        + request.getQuantity()
        );

        ticketTypeRepository.save(ticketType);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );

        return bookingRepository.findByUser_Id(
                user.getId()
        );
    }

    @Transactional
    public Booking cancelBooking(
            Long bookingId,
            String email) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElse(null);

        if (booking == null) {
            throw new ResourceNotFoundException(
                    "Booking not found with id: " + bookingId
            );
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        )
                );

        if (!booking.getUser()
                .getId()
                .equals(user.getId())) {

            throw new ForbiddenException(
                    "You are not allowed to cancel this booking"
            );
        }


        if ("CANCELLED".equals(booking.getStatus())) {
            return booking;
        }

        if (booking.getTicketType()
                .getEvent()
                .getEventDate()
                .isBefore(LocalDateTime.now())) {

            throw new BusinessException(
                    "Cannot cancel a booking for an event that has already occurred"
            );
        }

        TicketType ticketType =
                booking.getTicketType();

        ticketType.setQuantitySold(
                ticketType.getQuantitySold()
                        - booking.getQuantity()
        );

        booking.setStatus("CANCELLED");

        ticketTypeRepository.save(ticketType);

        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long bookingId) {

        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: "
                                        + bookingId
                        )
                );
    }
}
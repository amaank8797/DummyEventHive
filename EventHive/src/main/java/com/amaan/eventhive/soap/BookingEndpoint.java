package com.amaan.eventhive.soap;

import com.amaan.eventhive.entity.Booking;
import com.amaan.eventhive.service.BookingService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookingEndpoint {

    private static final String NAMESPACE_URI =
            "http://eventhive.com/booking";

    private final BookingService bookingService;

    public BookingEndpoint(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "GetBookingRequest"
    )
    @ResponsePayload
    public GetBookingResponse getBooking(
            @RequestPayload GetBookingRequest request) {

        Booking booking =
                bookingService.getBookingById(
                        request.getBookingId()
                );

        GetBookingResponse response =
                new GetBookingResponse();

        response.setBookingId(
                booking.getBookingId()
        );

        response.setUserId(
                booking.getUser().getId()
        );

        response.setTicketTypeId(
                booking.getTicketType().getTicketTypeId()
        );

        response.setQuantity(
                booking.getQuantity()
        );

        response.setTotalAmount(
                booking.getTotalAmount()
        );

        response.setBookingDate(
                booking.getBookingDate()
        );

        response.setStatus(
                booking.getStatus()
        );

        return response;
    }
}
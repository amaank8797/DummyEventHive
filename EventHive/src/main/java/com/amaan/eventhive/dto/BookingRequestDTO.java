package com.amaan.eventhive.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookingRequestDTO {

    @NotNull(message = "Ticket type ID is required")
    private Long ticketTypeId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public BookingRequestDTO() {
    }

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
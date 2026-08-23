package com.amaan.eventhive.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long bookingId;
    private Long userId;
    private Long ticketTypeId;
    private Integer quantity;
    private BigDecimal totalAmount;
    private LocalDateTime bookingDate;
    private String status;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long bookingId, Long userId, Long ticketTypeId,
                              Integer quantity, BigDecimal totalAmount,
                              LocalDateTime bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.ticketTypeId = ticketTypeId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
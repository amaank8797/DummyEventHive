package com.amaan.eventhive.dto;

import java.math.BigDecimal;

public class TicketTypeResponseDTO {

    private Long ticketTypeId;
    private String name;
    private BigDecimal price;
    private Integer totalQuantity;
    private Integer quantitySold;

    public TicketTypeResponseDTO() {
    }

    public TicketTypeResponseDTO(Long ticketTypeId, String name,
                                 BigDecimal price, Integer totalQuantity,
                                 Integer quantitySold) {
        this.ticketTypeId = ticketTypeId;
        this.name = name;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.quantitySold = quantitySold;
    }

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }
}
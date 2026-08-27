package com.amaan.eventhive.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "GetBookingRequest",
        namespace = "http://eventhive.com/booking"
)
public class GetBookingRequest {

    @XmlElement(
            name = "bookingId",
            namespace = "http://eventhive.com/booking"
    )
    private Long bookingId;

    public GetBookingRequest() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
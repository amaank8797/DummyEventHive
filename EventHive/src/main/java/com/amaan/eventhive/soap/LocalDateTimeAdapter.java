package com.amaan.eventhive.soap;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter
        extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String value) {
        return LocalDateTime.parse(value);
    }

    @Override
    public String marshal(LocalDateTime value) {
        return value != null ? value.toString() : null;
    }
}
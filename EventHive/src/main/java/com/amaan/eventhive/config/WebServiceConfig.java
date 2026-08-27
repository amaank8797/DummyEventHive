package com.amaan.eventhive.config;

import com.amaan.eventhive.soap.GetBookingRequest;
import com.amaan.eventhive.soap.GetBookingResponse;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet>
    messageDispatcherServlet(ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet =
                new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(
                servlet,
                "/ws/*"
        );
    }

    @Bean(name = "booking")
    public DefaultWsdl11Definition defaultWsdl11Definition(
            XsdSchema bookingSchema) {

        DefaultWsdl11Definition definition =
                new DefaultWsdl11Definition();

        definition.setPortTypeName("BookingPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace(
                "http://eventhive.com/booking"
        );
        definition.setSchema(bookingSchema);

        return definition;
    }

    @Bean
    public XsdSchema bookingSchema() {

        return new SimpleXsdSchema(
                new org.springframework.core.io.ClassPathResource(
                        "xsd/booking.xsd"
                )
        );
    }

    @Bean
    public Jaxb2Marshaller marshaller() {

        Jaxb2Marshaller marshaller =
                new Jaxb2Marshaller();

        marshaller.setClassesToBeBound(
                GetBookingRequest.class,
                GetBookingResponse.class
        );

        return marshaller;
    }
}
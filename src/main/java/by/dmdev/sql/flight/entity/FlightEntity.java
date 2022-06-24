package by.dmdev.sql.flight.entity;

public record FlightEntity(Long id,
                           String flightNo,
                           LocalDateTime departureDate,
                           String departureAirportCode,
                           LocalDateTime arrivalDate,
                           String arrivalAirportCode,
                           Integer aircraftId,
                           String status) {
}

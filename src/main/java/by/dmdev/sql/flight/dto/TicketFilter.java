package by.dmdev.sql.flight.dto;

// record - это возможность JDK16, автоматически генерирует конструктор, реализовывает equals, hashcode.
// У него нет setter т.к. этот объект - неизменяемый (immutable).
public record TicketFilter(int limit,
                           int offset,
                           String passengerName,
                           String seatNo) {
}

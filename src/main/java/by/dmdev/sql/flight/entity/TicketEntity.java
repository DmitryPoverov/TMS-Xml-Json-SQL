package by.dmdev.sql.flight.entity;

import java.math.BigDecimal;

public class TicketEntity {

    private Long ticketId;
    private String passengerNo;
    private String passengerName;
    private FlightEntity flight;
    private String seatNo;
    private BigDecimal cost;

    public TicketEntity() {
    }

    public TicketEntity(Long ticketId, String passengerNo, String passengerName, FlightEntity flight, String seatNo, BigDecimal cost) {
        this.ticketId = ticketId;
        this.passengerNo = passengerNo;
        this.passengerName = passengerName;
        this.flight = flight;
        this.seatNo = seatNo;
        this.cost = cost;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
    public void setPassengerNo(String passengerNo) {
        this.passengerNo = passengerNo;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getTicketId() {
        return ticketId;
    }
    public String getPassengerNo() {
        return passengerNo;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public FlightEntity getFlight() {
        return flight;
    }
    public String getSeatNo() {
        return seatNo;
    }
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ticket: " +
                "ticketId=" + ticketId +
                ", passengerNo='" + passengerNo + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", flight: " + flight +
                ", seatNo='" + seatNo + '\'' +
                ", cost=" + cost +
                ".";
    }
}

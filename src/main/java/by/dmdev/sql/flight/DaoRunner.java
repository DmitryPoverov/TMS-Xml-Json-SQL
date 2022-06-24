package by.dmdev.sql.flight;

import by.dmdev.sql.flight.dao.TicketDAO;
import by.dmdev.sql.flight.dto.TicketFilter;
import by.dmdev.sql.flight.entity.TicketEntity;

import java.math.BigDecimal;

public class DaoRunner {
    public static void main(String[] args) {

//        deleteTest(61L);
//        saveTest();
//        updateTest();
//        findAll();
//        findAllLimitOffset();
        selectById();
    }

    private static void selectById() {
        var ticket = TicketDAO.getInstance().selectById(60L);
        System.out.println(ticket);
    }

    private static void findAllLimitOffset() {
        var ticketFilter = new TicketFilter(10, 0, "Иван", "С");
        var tickets = TicketDAO.getInstance().findAll(ticketFilter);
        var iterator = tickets.iterator();
        if (tickets.size() == 0) {
            System.out.println(tickets);
        }
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void findAll() {
        var instance = TicketDAO.getInstance().findAll();
        var tickets = instance.iterator();
        while (tickets.hasNext()) {
            System.out.println(tickets.next());
        }
    }

    private static void updateTest() {
        var instance = TicketDAO.getInstance();
        var maybeTicketEntity = instance.selectById(2L);
        System.out.println(maybeTicketEntity);

        maybeTicketEntity.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(188.88));
            instance.update(ticket);
        });
    }

    private static void deleteTest(Long id) {
// Удаление билета по ID, который мы указываем.
        var ticketDAO = TicketDAO.getInstance();
        var deleteResult = ticketDAO.delete(id);
        System.out.println(deleteResult? "The ticket saved." : "The ticket didn't save...");
    }

    private static void saveTest() {
// Объявить переменную, с ней связать единственную сущность, через SET установить поля этой сущности.
        var instance = TicketDAO.getInstance();
        var ticket = new TicketEntity();
        ticket.setPassengerNo("24143");
        ticket.setPassengerName("Selma Bouvier");
//        ticket.setFlight(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);

//  Добавление в БД заранее заранее заполненной сущности.
        var saveTicket = instance.save(ticket);
        System.out.println(saveTicket);
    }
}

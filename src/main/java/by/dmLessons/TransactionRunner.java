package by.dmLessons;

import by.dmLessons.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {

    public static void main(String[] args) throws SQLException {

        long flightId = 8;
        var deleteFlightSql = "DELETE FROM flight_schema.flight WHERE id = ?";
        var deleteTicketsSql = "DELETE FROM flight_schema.ticket WHERE flight_id = ?";

        Connection connection = null;
        PreparedStatement deleteFlightStatement = null;
        PreparedStatement deleteTicketStatement = null;

        try {
            connection = ConnectionManager.get();
            deleteFlightStatement = connection.prepareStatement(deleteFlightSql);
            deleteTicketStatement = connection.prepareStatement(deleteTicketsSql);

// Отключает автоматическое внесение изменений в таблицу
            connection.setAutoCommit(false);

// Заполнили 1-ое (и единственное) поле в первом и втором PreparedStatement;
            deleteFlightStatement.setLong(1, flightId);
            deleteTicketStatement.setLong(1, flightId);

// Запустили удаление строки из таблицы с билетами
            var deletedTicketResult = deleteTicketStatement.executeUpdate();

// Генерируем искусственно исключение. Без if ошибка "Недостижимое условие".
            if (true) {
                throw new RuntimeException("*** An artificial exception. ***");
            }
// После этого исключения выполнение удаления таблицы с полетами не произойдет
// Получается что строка из билетов уже удалится, а из полетов соответствующие строки не удалятся
            var deletedFlightResult = deleteFlightStatement.executeUpdate();

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteFlightStatement != null) {
                deleteFlightStatement.close();
            }
            if (deleteTicketStatement != null) {
                deleteTicketStatement.close();
            }
        }
    }
}

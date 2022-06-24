package by.dmdev.sql.lessons;

import by.dmdev.sql.lessons.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchQueryRunner {

    public static void main(String[] args) throws SQLException {

        long flightId = 8L;
        var deleteFlightSql = "DELETE FROM flight_schema.flight WHERE id = " + flightId;
        var deleteTicketsSql = "DELETE FROM flight_schema.ticket WHERE flight_id = " + flightId;

        Connection connection = null;
        Statement statement = null;

        try {
// Batch-операции выполняются не по одной, а всей "пачкой" (Batch - Пачка),
// экономя таким образом время отправки запросов, т.к. нет пересылки каждой операции по отдельности.
// Batch подходит для DDL
            connection = ConnectionManager.get();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(deleteTicketsSql);
            statement.addBatch(deleteFlightSql);

// В случае с 8 рейсом Sout выведет 6 и 1, т.е. удалено 6 билетов, которые ссылаются на 1 рейс.
            var ints = statement.executeBatch();
            for (int i: ints) {
                System.out.println(i);
            }

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
            if (statement != null) {
                statement.close();
            }
        }
    }
}

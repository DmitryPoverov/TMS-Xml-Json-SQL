package by.dmdev.sql.lessons;

import by.dmdev.sql.lessons.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
/*        String sql = """
                INSERT INTO flight_schema.info (data)
                VALUES
                ('TestTEST'),
                ('TestTEST');
                """;*/
/*        String sql = """
                DELETE FROM info
                WHERE id>=15 and id<=34
                """;

        String sql = """
                INSERT INTO info (data)
                VALUES
                ('Test_Test4'),
                ('Test_Test4');
                """;

        String sql1 = """
                SELECT *
                FROM flight_schema.airport;
                """;

        String sql2 = """
                DELETE FROM info
                WHERE id>=35 AND id <=45;
                """;

        String sql3 = """
                INSERT INTO flight_repository.flight_schema.airport (code, country, city)
                VALUES ('MNK', 'Belarus', 'Minsk'),
                       ('LND', 'England', 'London'),
                       ('MSK', 'Russia', 'Moscow'),
                       ('BSL', 'Spain', 'Barcelona');

                INSERT INTO flight_repository.flight_schema.aircraft (model)
                VALUES ('Boeing 777-300'),
                       ('Boeing 737-300'),
                       ('Airbus A320-200'),
                       ('SuperJet-100');

                INSERT INTO flight_repository.flight_schema.seat (aircraft_id, seat_no)
                VALUES (1, 'A1'),
                       (1, 'A2'),
                       (1, 'B1'),
                       (1, 'B2'),
                       (1, 'C1'),
                       (1, 'C2'),
                       (1, 'D1'),
                       (1, 'D2'),
                       (2, 'A1'),
                       (2, 'A2'),
                       (2, 'B1'),
                       (2, 'B2'),
                       (2, 'C1'),
                       (2, 'C2'),
                       (2, 'D1'),
                       (2, 'D2'),
                       (3, 'A1'),
                       (3, 'A2'),
                       (3, 'B1'),
                       (3, 'B2'),
                       (3, 'C1'),
                       (3, 'C2'),
                       (3, 'D1'),
                       (3, 'D2'),
                       (4, 'A1'),
                       (4, 'A2'),
                       (4, 'B1'),
                       (4, 'B2'),
                       (4, 'C1'),
                       (4, 'C2'),
                       (4, 'D1'),
                       (4, 'D2');

                INSERT INTO flight_repository.flight_schema.flight
                (flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status)
                VALUES ('MN3002', '2020-06-14T14:30', 'MNK', '2020-06-14T18:07', 'LND', 1, 'ARRIVED'),
                       ('MN3002', '2020-06-16T09:15', 'LND', '2020-06-16T13:00', 'MNK', 1, 'ARRIVED'),
                       ('BC2801', '2020-07-28T23:25', 'MNK', '2020-07-29T02:43', 'LND', 2, 'ARRIVED'),
                       ('BC2801', '2020-08-01T11:00', 'LND', '2020-08-01T14:15', 'MNK', 2, 'DEPARTED'),
                       ('TR3103', '2020-05-03T13:10', 'MSK', '2020-05-03T18:38', 'BSL', 3, 'ARRIVED'),
                       ('TR3103', '2020-05-10T07:15', 'BSL', '2020-05-10T012:44', 'MSK', 3, 'CANCELLED'),
                       ('CV9827', '2020-09-09T18:00', 'MNK', '2020-09-09T19:15', 'MSK', 4, 'SCHEDULED'),
                       ('CV9827', '2020-09-19T08:55', 'MSK', '2020-09-19T10:05', 'MNK', 4, 'SCHEDULED'),
                       ('QS8712', '2020-12-18T03:35', 'MNK', '2020-12-18T06:46', 'LND', 2, 'ARRIVED');

                INSERT INTO flight_repository.flight_schema.ticket (passenger_no, passenger_name, flight_id, seat_no, cost)
                VALUES ('112233', 'Иван Иванов', 1, 'A1', 200),
                       ('23234A', 'Петр Петров', 1, 'B1', 180),
                       ('SS988D', 'Светлана Светикова', 1, 'B2', 175),
                       ('QYASDE', 'Андрей Андреев', 1, 'C2', 175),
                       ('POQ234', 'Иван Кожемякин', 1, 'D1', 160),
                       ('898123', 'Олег Рубцов', 1, 'A2', 198),
                       ('555321', 'Екатерина Петренко', 2, 'A1', 250),
                       ('QO23OO', 'Иван Розмаринов', 2, 'B2', 225),
                       ('9883IO', 'Иван Кожемякин', 2, 'C1', 217),
                       ('123UI2', 'Андрей Буйнов', 2, 'C2', 227),
                       ('SS988D', 'Светлана Светикова', 2, 'D2', 277),
                       ('EE2344', 'Дмитрий Трусцов', 3, 'А1', 300),
                       ('AS23PP', 'Максим Комсомольцев', 3, 'А2', 285),
                       ('322349', 'Эдуард Щеглов', 3, 'B1', 99),
                       ('DL123S', 'Игорь Беркутов', 3, 'B2', 199),
                       ('MVM111', 'Алексей Щербин', 3, 'C1', 299),
                       ('ZZZ111', 'Денис Колобков', 3, 'C2', 230),
                       ('234444', 'Иван Старовойтов', 3, 'D1', 180),
                       ('LLLL12', 'Людмила Старовойтова', 3, 'D2', 224),
                       ('RT34TR', 'Степан Дор', 4, 'A1', 129),
                       ('999666', 'Анастасия Шепелева', 4, 'A2', 152),
                       ('234444', 'Иван Старовойтов', 4, 'B1', 140),
                       ('LLLL12', 'Людмила Старовойтова', 4, 'B2', 140),
                       ('LLLL12', 'Роман Дронов', 4, 'D2', 109),
                       ('112233', 'Иван Иванов', 5, 'С2', 170),
                       ('NMNBV2', 'Лариса Тельникова', 5, 'С1', 185),
                       ('DSA586', 'Лариса Привольная', 5, 'A1', 204),
                       ('DSA583', 'Артур Мирный', 5, 'B1', 189),
                       ('DSA581', 'Евгений Кудрявцев', 6, 'A1', 204),
                       ('EE2344', 'Дмитрий Трусцов', 6, 'A2', 214),
                       ('AS23PP', 'Максим Комсомольцев', 6, 'B2', 176),
                       ('112233', 'Иван Иванов', 6, 'B1', 135),
                       ('309623', 'Татьяна Крот', 6, 'С1', 155),
                       ('319623', 'Юрий Дувинков', 6, 'D1', 125),
                       ('322349', 'Эдуард Щеглов', 7, 'A1', 69),
                       ('DIOPSL', 'Евгений Безфамильная', 7, 'A2', 58),
                       ('DIOPS1', 'Константин Швец', 7, 'D1', 65),
                       ('DIOPS2', 'Юлия Швец', 7, 'D2', 65),
                       ('1IOPS2', 'Ник Говриленко', 7, 'C2', 73),
                       ('999666', 'Анастасия Шепелева', 7, 'B1', 66),
                       ('23234A', 'Петр Петров', 7, 'C1', 80),
                       ('QYASDE', 'Андрей Андреев', 8, 'A1', 100),
                       ('1QAZD2', 'Лариса Потемнкина', 8, 'A2', 89),
                       ('5QAZD2', 'Карл Хмелев', 8, 'B2', 79),
                       ('2QAZD2', 'Жанна Хмелева', 8, 'С2', 77),
                       ('BMXND1', 'Светлана Хмурая', 8, 'В2', 94),
                       ('BMXND2', 'Кирилл Сарычев', 8, 'D1', 81),
                       ('SS988D', 'Светлана Светикова', 9, 'A2', 222),
                       ('SS978D', 'Андрей Желудь', 9, 'A1', 198),
                       ('SS968D', 'Дмитрий Воснецов', 9, 'B1', 243),
                       ('SS958D', 'Максим Гребцов', 9, 'С1', 251),
                       ('112233', 'Иван Иванов', 9, 'С2', 135),
                       ('NMNBV2', 'Лариса Тельникова', 9, 'B2', 217),
                       ('23234A', 'Петр Петров', 9, 'D1', 189),
                       ('123951', 'Полина Зверева', 9, 'D2', 234);
                """;*/
/*//                ('Test_Test-4');
//        String sql = """
//                INSERT INTO info (data)
//                VALUES
//                ('TestTest'),
//                ('TestTest'),
//                ('TestTest'),
//                ('TestTest'),
//                ('TestTest');
//                """;
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Schema is: " + connection.getSchema());
//            it's possible to execute several queries (several CREATE, DELETE etc.)
//            type var instead of the Statement
//            var result = statement.execute(sql);  // true if - SELECT operation, false - otherwise.
//            type var instead of the "Statement" or the "int"

//            var result = statement.executeQuery(sql1); // to use ResultSet for selections of results
//
//            while (result.next()) {  // "get-..." methods possible to use ONLY after next()
//                System.out.print(result.getLong("ticket_id") + ", ");
//                System.out.print(result.getString("passenger_no") + ", ");
//                System.out.print(result.getString("passenger_name") + ", ");
//                System.out.print(result.getLong("flight_id") + ", ");
//                System.out.print(result.getString("seat_no") + ", ");
//                System.out.println(result.getBigDecimal("cost") + ".");
//                System.out.println("-------------------------------------------------");
//            }
// executeUpdate() возвращает int
            int result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            System.out.println("Result = " + result);

            //it's not necessarily cause executeUpdate returns "int"
            System.out.println("Item(s) was updated: " + statement.getUpdateCount());

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("Generated key is: " + generatedKeys.getInt("id"));
            }
        }*/
/*        try (Connection connect = ConnectionManager.open();
             Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            var result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Lines that were updated: " + result);

            System.out.println("Item(s) was updated: " + statement.getUpdateCount());

            var generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("Generated key is: " + generatedKeys.getInt("id"));
                while (generatedKeys.next()) {
                System.out.println("Generated key is: " + generatedKeys.getString("data"));
            }
            }
        }*/
/*        try (Connection connect = ConnectionManager.open();
             Statement statement = connect.createStatement()) {
        }*/
/*            var result = statement.executeQuery(sql1);
            while (result.next()) {
                System.out.println("Lines: " + result.getString(1));*/
/*            var result = statement.execute(sql1);
            System.out.println("ResultSet: " + result);*/
/*            var result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getLong("ticket_id"));
                System.out.println(result.getString("passenger_no"));
                System.out.println(result.getString("passenger_name"));
                System.out.println(result.getLong("flight_id"));
                System.out.println(result.getString("seat_no"));
                System.out.println(result.getBigDecimal("cost"));
                System.out.println("-------------------");
            };
        }*/
/*        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {

            int executeResult = statement.executeUpdate(sql);

            System.out.println(executeResult);
            System.out.println(statement.getUpdateCount());   //it's not necessarily cause executeUpdate returns "int"

        }*/
/*            var execute = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            var generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("Index N = " + generatedKeys.getInt("id"));
            }*/
/*----------------------------------------------------------------------------------------------------------------------
        String flight_id = "2";
        var ticketByFlightId = getTicketByFlightId(flight_id);
        System.out.println(ticketByFlightId);
    }

    private static List<Long> getTicketByFlightId (String flightId) throws SQLException {

        String sql = """
                SELECT ticket_id
                FROM ticket
                WHERE flight_id = %s
                """.formatted(flightId);

        List<Long> result = new ArrayList<>();

        try (var open = ConnectionManager.open();
             var statement = open.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
// Защита от NULL, он здесь не опасен т.к. ticket_id - это PrimaryKey (он не может быть NULL),
// а еще ticket_id - это примитивный, а не сложный тип.
                result.add(resultSet.getObject("ticket_id", Long.class));
//                result.add(resultSet.getLong("ticket_id));
            }
        }
        return result;
------------------------------------------------------------------------------------------------------------------------
        */
/*        Long flight_id = 2L;
        var ticketByFlightId = getTicketByFlightId(flight_id);
        System.out.println(ticketByFlightId);*/

        var flightsBetween = getFlightsBetween
                (LocalDate.of(2020, 1, 1).atStartOfDay(), LocalDateTime.now());

        System.out.println(flightsBetween);

        try {
            checkMetaData();
        } finally {
            ConnectionManager.closePool();
        }

    }


// Catalog - это название базы дынных, к которой мы работаем через подключение.
    private static void checkMetaData() throws SQLException {
        try (var open = ConnectionManager.get()) {
            var metaData = open.getMetaData();
/*
            var catalogs = metaData.getCatalogs();

            while (catalogs.next()) {
                System.out.println("\t" + catalogs.getString("TABLE_CAT")); // или 1. (cat - catalog)

                var schemas = metaData.getSchemas();
                while (schemas.next()) {
                    System.out.println("\t\t" + schemas.getString("TABLE_SCHEM")); // или 1.
                }        var tables = metaData.getTables(null, null, "%", null);
                if (schemas.equals("flight_schema"))
                   while (tables.next()) {
                       System.out.println(tables.getString("TABLE_NAME"));
                   }
            }*/
            var databaseProductName = metaData.getDatabaseProductName();
            System.out.println("1) metaData.getDatabaseProductName(): " + databaseProductName);
            var catalogs = metaData.getCatalogs();
            while (catalogs.next()) {
                var catalog = catalogs.getString(1);
                System.out.println("\t2) catalogs.getString(1): " + catalog);

                var schemas1 = metaData.getSchemas();
                while (schemas1.next()) {
                    var schema = schemas1.getString(1);
                    System.out.println("\t\t3) schemas1.getString(1): " + schema);

// Выведем только таблицы, содержащиеся в схеме flight_schema и которые относятся к типу "TABLE"
                    var tables = metaData.getTables(catalog, schema, "%",  new String[] {"TABLE"});
                    if (schema.equals("flight_schema")) {
                        while (tables.next()) {
                            var table = tables.getString(3);
                            System.out.println("\t\t\t4) tables.getString(3): " + table);
                            if (table.equals("aircraft")) {
                                var columns = metaData.getColumns(catalog, schema, "%", null);
                                while (columns.next()) {
                                    var column = columns.getString(4);
                                    System.out.println("5) metaData.getColumns(null, null, \"%\", null): " + column);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) throws SQLException {

        String sql = """
                SELECT id
                FROM flight_schema.flight
                WHERE departure_date BETWEEN ? AND ?
                """;

        List<Long> result = new ArrayList<>();

        try (var open = ConnectionManager.get();
             var preparedStatement = open.prepareStatement(sql)){

// Параметр для экономии ОП, он указывает сколько строк берется за одну итерацию.
            preparedStatement.setFetchSize(2);
// Ограничение тайм-аута ожидания.
            preparedStatement.setQueryTimeout(10);
// Ограничение максимального числа строк, которые будут выведены в результат.
            preparedStatement.setMaxRows(5);

            System.out.println(preparedStatement);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(start));
            System.out.println(preparedStatement);

            preparedStatement.setTimestamp(2, Timestamp.valueOf(end));
            System.out.println(preparedStatement);

            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getLong("id"));
            }
        }
        return result;
    }
//----------------------------------------------------------------------------------------------------------------------
/*    private static List<Long> getTicketByFlightId (Long flightId) throws SQLException {

        String sql = """
                SELECT ticket_id
                FROM flight_schema.ticket
                WHERE flight_id = ?
                """;

        String sql = """
                SELECT ticket_id
                FROM flight_schema.ticket
                WHERE flight_id = ?
                """;

        List<Long> result = new ArrayList<>();

        try (var open = ConnectionManager.open();
             var preparedStatement = open.prepareStatement(sql)) {
            preparedStatement.setLong(1, flightId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
// Защита от NULL, он здесь не опасен т.к. ticket_id - это PrimaryKey (он не может быть NULL),
// а еще ticket_id - это примитивный, а не сложный тип.
                result.add(resultSet.getObject("ticket_id", Long.class));
//                result.add(resultSet.getLong("ticket_id));
            }
        }
        return result;
    }*/
}

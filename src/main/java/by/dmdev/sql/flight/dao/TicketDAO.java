package by.dmdev.sql.flight.dao;

import by.dmdev.sql.flight.entity.FlightEntity;
import by.dmdev.sql.flight.dto.TicketFilter;
import by.dmdev.sql.flight.entity.TicketEntity;
import by.dmdev.sql.flight.exception.DaoException;
import by.dmdev.sql.lessons.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class TicketDAO implements DAO<Long, TicketEntity> {

// Простая реализация SingleTone, для исключения создания нескольких реализаций/instance
    private static final TicketDAO INSTANCE = new TicketDAO();

    private static final String DELETE_SQL = """
            DELETE FROM flight_repository.flight_schema.ticket
            WHERE ticket_id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO flight_repository.flight_schema.ticket(passenger_no, passenger_name, flight_id, seat_no, cost)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE flight_repository.flight_schema.ticket
            SET
                passenger_no = ?,
                passenger_name = ?,
                flight_id = ?,
                seat_no = ?,
                cost = ?
            WHERE ticket_id = ?
            """;

// Записали SELECT запрос на вывод всех строк.
    private static final String FIND_ALL_SQL = """
            SELECT ticket_id,
                passenger_no,
                passenger_name,
                flight_id,
                seat_no,
                cost,
                f.id,
                f.flight_no,
                f.status,
                f.aircraft_id,
                f.arrival_airport_code,
                f.arrival_date,
                f.departure_airport_code,
                f.departure_date
            FROM flight_repository.flight_schema.ticket t
            JOIN flight_repository.flight_schema.flight f ON flight_id = f.id
            """;

// Сконкатенировали запрос на поиск всех строк с параметром фильтрации.
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE ticket_id = ?
            """;

// Privat конструктор не позволит создать еще один экземпляр DAO
    private TicketDAO() {
    }

// Чтобы получить ту самую единственную реализацию DAO используем getter, возвращающий наш статический экземпляр.
    public static TicketDAO getInstance() {
        return INSTANCE;
    }
    public static FlightDAO flightDAO = FlightDAO.getInstance();

// Версия кода с вынесенным отдельным методом, который собирает и возвращает целый билет.
// Одинаковый блок кода, его можно вынести в отдельный метод buildTicket().
// Это версия поиска всех записей с учетом вынесенного метода  buildTicket().
//
//    public List<Ticket> findAll() {
//        try (var connection = ConnectionManager.get();
//             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
//            var resultSet = preparedStatement.executeQuery();
//            List<Ticket> tickets = new ArrayList<>();
//            while (resultSet.next()) {
//                tickets.add(buildTicket(resultSet));
//            }
//            return tickets;
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//    }
//
// Это версия поиска записей по ID с учетом вынесенного метода buildTicket()
// public Optional<Ticket> findById(Long id) {
//        try (var connection = ConnectionManager.get();
//             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
//            preparedStatement.setLong(1, id);
//
//            var resultSet = preparedStatement.executeQuery();
//            Ticket ticket = null;
//            if (resultSet.next()) {
//                ticket = buildTicket(resultSet);
//            }
//
//            return Optional.ofNullable(ticket);
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//    }

     private TicketEntity buildTicket(ResultSet resultSet) throws SQLException {
        var flight = new FlightEntity(
                resultSet.getLong("id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getString("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                resultSet.getString("status")
        );
        return new TicketEntity(
                resultSet.getLong("ticket_id"),
                resultSet.getString("passenger_no"),
                resultSet.getString("passenger_name"),
                flightDAO.selectById(resultSet.getLong("id")).orElse(null),
                resultSet.getString("seat_no"),
                resultSet.getBigDecimal("cost")
        );
    }

    public List<TicketEntity> findAll(TicketFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSQL = new ArrayList<>();
        if (filter.passengerName() != null) {

// параметр "%" означает любой символ и нужен чтобы искать имя по любой части запроса %Иван%, %ван%, %ан% и т.д.
            whereSQL.add("passenger_name LIKE ?");
            parameters.add("%" + filter.passengerName() + "%");
        }
        if (filter.seatNo() != null) {
            whereSQL.add("seat_no LIKE ?");
            parameters.add("%" + filter.seatNo() + "%");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());
        var where = whereSQL.stream()
                .collect(joining(" AND ", "WHERE ", " LIMIT ? OFFSET ? "));

        var sql = FIND_ALL_SQL + where;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i+1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            List<TicketEntity> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<TicketEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<TicketEntity> tickets = new ArrayList<>();
            TicketEntity ticket;
            while (resultSet.next()) {
                var flight = new FlightEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departure_date").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                        resultSet.getString("arrival_airport_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status")
                );
                ticket = new TicketEntity(
                        resultSet.getLong("ticket_id"),
                        resultSet.getString("passenger_no"),
                        resultSet.getString("passenger_name"),
                        flight,
                        resultSet.getString("seat_no"),
                        resultSet.getBigDecimal("cost")
                );
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

// Чтобы не дублировать код в этом методе открываем соединение и передаем (его и id) в перегруженный метод.
    @Override
    public Optional<TicketEntity> selectById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return selectById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

// Перегружаем метод и добавляем ему на вход соединение чтобы не расходовать соединения из пула соединений.
    public Optional<TicketEntity> selectById(Long id, Connection connection) {
        try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();

            TicketEntity ticket = null;
            if (resultSet.next()) {
                ticket = buildTicket(resultSet);
            }
            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public void update(TicketEntity ticket) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().id());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());
            preparedStatement.setLong(6, ticket.getTicketId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public TicketEntity save(TicketEntity ticket) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().id());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticket.setTicketId(generatedKeys.getLong("ticket_id"));
            }
            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}


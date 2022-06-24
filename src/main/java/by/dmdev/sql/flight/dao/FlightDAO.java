package by.dmdev.sql.flight.dao;

import by.dmdev.sql.flight.entity.FlightEntity;
import by.dmdev.sql.flight.exception.DaoException;
import by.dmdev.sql.lessons.util.ConnectionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FlightDAO implements DAO<Long, FlightEntity> {

    private static final FlightDAO INSTANCE = new FlightDAO();

    private static final String SELECT_BY_ID_SQL = """
            SELECT id,
                flight_no,
                status,
                aircraft_id,
                arrival_airport_code,
                arrival_date,
                departure_airport_code,
                departure_date
                FROM flight_repository.flight_schema.flight
                WHERE id = ?
            """;

    private FlightDAO() {
    }

    public static FlightDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public FlightEntity save(FlightEntity ticket) {
        return null;
    }

    @Override
    public void update(FlightEntity ticket) {

    }

    @Override
    public List<FlightEntity> findAll() {
        return null;
    }

    @Override
    public Optional<FlightEntity> selectById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            FlightEntity flight = null;
            if (resultSet.next()) {
                flight = new FlightEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departure_date").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                        resultSet.getString("arrival_airport_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status")
                );
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

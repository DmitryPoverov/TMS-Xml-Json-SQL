package by.dmdev.sql.flight.exception;

public class DaoException extends RuntimeException{

    public DaoException(Throwable throwable){
        super(throwable);
    }
}

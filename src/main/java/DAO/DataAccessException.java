/**
 * DAO is data access object for inserting clearing and finding data within the database
 */
package DAO;

/**
 * Exception thrown for bad data access
 */
public class DataAccessException extends Exception{
    /**
     * Throwable error with message
     * @param message message
     */
    public DataAccessException( String message ) {
        super(message);
    }
}
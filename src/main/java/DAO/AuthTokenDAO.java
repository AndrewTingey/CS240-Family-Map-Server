/**
 * DAO is data access object for inserting clearing and finding data within the database
 */
package DAO;

import Model.Authtoken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for Authorization Token
 */
public class AuthTokenDAO {
    /**
     * connection to the database
     */
    private final Connection conn;

    /**
     * constructor with connection to the database passed in
     * @param conn connection to database
     */
    public AuthTokenDAO( Connection conn) {
        this.conn = conn;
    }


    /**
     * insert a authorization token into the database
     */
    public void insert(Authtoken authtoken) throws DataAccessException {
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES (?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public String insertByUsername( String username ) throws DataAccessException {
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES (?,?)";
        String authtoken = new Authtoken().generateAuthtoken();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            stmt.setString(2, username);

            stmt.executeUpdate();
            return authtoken;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * clear all authorization tokens from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Authtoken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Clear by username
     * @param associatedUsername
     * @throws DataAccessException
     */
    public void clearAll( String associatedUsername ) throws DataAccessException {
        String sql = "DELETE FROM Authtoken WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * find a specific authtoken from database
     * @param authToken string to be found
     * @return found authtoken
     */
    public Authtoken find(String authToken) throws DataAccessException {
        Authtoken authtoken;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtoken = new Authtoken(rs.getString("authtoken"), rs.getString("username"));
                return authtoken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public Authtoken findAuthtoken( String username ) throws DataAccessException {
        Authtoken authtoken;
        ResultSet rs;
        String sql = "SELECT * FROM Authtoken WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtoken = new Authtoken(rs.getString("authtoken"), rs.getString("username"));
                return authtoken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

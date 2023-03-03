/**
 * DAO is data access object for inserting clearing and finding data within the database
 */
package DAO;

import Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Person
 */
public class PersonDAO {

    /**
     * connection to database
     */
    private final Connection conn;

    /**
     * constructor with connection to the database passed in
     * @param conn connection to database
     */
    public PersonDAO( Connection conn ) {
        this.conn = conn;
    }

    /**
     * inserts person into the database
     * @param person person to be added
     */
    public void insert(Person person)  throws DataAccessException {
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, " +
                "fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting an person into the database");
            //throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * clears persons from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }

    /**
     * clears people from database who match a username
     */
    public void clearAll(String associatedUsername) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE associatedUsername = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }

    /**
     * finds person from database
     * @param personID person to be found
     * @return Person object if found
     */
    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while finding a person in the database");
        }
    }

    /**
     * finds all people who match the username
     * @param associatedUsername
     * @return List<Person>
     * @throws DataAccessException
     */
    public List<Person> findAll (String associatedUsername) throws DataAccessException {
        List<Person> personList = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Person personToAdd = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                personList.add(personToAdd);
            }
            return personList;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

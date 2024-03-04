package edu.javaLessons.City.Dao;

import edu.javaLessons.City.CheckPersonException;
import edu.javaLessons.City.domian.PersonRequest;
import edu.javaLessons.City.domian.PersonResponse;

import java.sql.*;

public class PersonCheckDao {

    private static final String SQL_REQUEST = "SELECT temporal FROM cr_address_person ap \n" +
            "INNER JOIN cr_person p ON p.person_id = ap.person_id \n" +
            "INNER JOIN cr_address a ON a.address_id = ap.address_id \n" +
            "WHERE \n" +
            "    CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date OR ap.end_date IS NULL) AND \n" +
            "    UPPER(p.sur_name COLLATE \"C\") = UPPER(? COLLATE \"C\") AND \n" +
            "    UPPER(p.given_name COLLATE \"C\") = UPPER(? COLLATE \"C\") AND \n" +
            "    UPPER(patronymic COLLATE \"C\") = UPPER(? COLLATE \"C\") AND \n" +
            "    p.date_of_birth = ? AND \n" +
            "    a.street_code = ? AND \n" +
            "    UPPER(a.building COLLATE \"C\") = UPPER(? COLLATE \"C\") " ;

    public PersonResponse checkPerson(PersonRequest request) throws CheckPersonException{
        PersonResponse response =  new PersonResponse();

            String sql = SQL_REQUEST;

            if(request.getExtension() != null){
                sql += "and UPPER(a.extension COLLATE \"C\") = UPPER(? COLLATE \"C\") " ;
            } else {
                sql += "and extension is null ";
            }
            if(request.getApartment() != null){
                sql += "and UPPER(a.apartment COLLATE \"C\") = UPPER(? COLLATE \"C\"); ";
            } else {
                sql += "and a.apartment is null ";
            }

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            int count = 1;

            stmt.setString(count++, request.getSurName());
            stmt.setString(count++, request.getGivenName());
            stmt.setString(count++, request.getPatronymic());
            stmt.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(count++, request.getStreetCode());
            stmt.setString(count++, request.getBuilding());
            if(request.getExtension() != null) {
                stmt.setString(count++, request.getExtension());
            }
            if(request.getApartment() != null) {
                stmt.setString(count, request.getApartment());
            }
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("Temporal"));
            }

        } catch (SQLException ex){
            throw new CheckPersonException(ex);
        }
        return response;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost/CityRegister ",
                "postgres", "postgres" );

    }
}

package Dao;

import Exceptions.CheckPersonException;
import domian.PersonRequest;
import domian.PersonResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    private static final String SQL_REQUEST = " ";

    public PersonResponse checkPerson(PersonRequest request) throws CheckPersonException{
        PersonResponse response =  new PersonResponse();
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)){

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

    private Connection getConnection() {
        return null;
    }
}

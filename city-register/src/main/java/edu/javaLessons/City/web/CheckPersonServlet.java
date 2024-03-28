package edu.javaLessons.City.web;

import edu.javaLessons.City.CheckPersonException;
import edu.javaLessons.City.Dao.PersonCheckDao;
import edu.javaLessons.City.Dao.PoolConnectionBuilder;
import edu.javaLessons.City.domian.PersonRequest;
import edu.javaLessons.City.domian.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CheckPersonServlet", urlPatterns = "/checkPerson")
public class CheckPersonServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);

    private PersonCheckDao dao;
    @Override
    public void init() {
        logger.trace("Servlet is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      request.setCharacterEncoding("UTF-8");

      String surname = request.getParameter("surname");

        PersonRequest pr = new PersonRequest();
        pr.setSurName(surname);
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995,3,18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtensions("2");
        pr.setApartment("121");

        try {
            PersonResponse ps = dao.checkPerson(pr);
            if(ps.isRegistered()){
                response.getWriter().write("Registered");
            }else{
                response.getWriter().write("Not Registered");
            }
        } catch (CheckPersonException e) {
            throw new RuntimeException(e);
        }
    }
}
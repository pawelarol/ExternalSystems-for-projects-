package edu.javaLessons.City.web;

import edu.javaLessons.City.CheckPersonException;
import edu.javaLessons.City.Dao.PersonCheckDao;
import edu.javaLessons.City.domian.PersonRequest;
import edu.javaLessons.City.domian.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CheckPersonServlet", urlPatterns = "/checkPerson")
public class CheckPersonServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      request.setCharacterEncoding("UTF-8");

      logger.trace("Test");

      String surname = request.getParameter("surname");

        PersonRequest pr = new PersonRequest();
        pr.setSurName(surname);
        pr.setGivenName("Pavel");
        pr.setPatronymic("Sergeevich");
        pr.setDateOfBirth(LocalDate.of(1998,7,12));
        pr.setStreetCode(15-161);
        pr.setBuilding("9");
        pr.setExtension("1");
        pr.setApartment("2");

        try {
            PersonCheckDao dao = new PersonCheckDao();
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
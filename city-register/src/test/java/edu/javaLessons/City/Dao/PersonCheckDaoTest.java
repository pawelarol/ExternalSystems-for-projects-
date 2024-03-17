package edu.javaLessons.City.Dao;

import edu.javaLessons.City.CheckPersonException;
import edu.javaLessons.City.domian.PersonRequest;
import edu.javaLessons.City.domian.PersonResponse;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCheckDaoTest {

    @Test
    public void checkPerson() throws CheckPersonException {

        PersonRequest pr = new PersonRequest();
        pr.setSurName("Васильев");
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtensions("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps = dao.checkPerson(pr);
        Assert.assertTrue(ps.isRegistered());
        Assert.assertFalse(ps.isTemporal());
    }
  // @Test
//  public void checkPerson2() throws CheckPersonException {
//
//        PersonRequest pr = new PersonRequest();
//        pr.setSurName("Arol");
//        pr.setGivenName("Pavel");
//        pr.setPatronymic("Sergeevich");
//        pr.setDateOfBirth(LocalDate.of(1998, 7, 12));
//        pr.setStreetCode(1);
//        pr.setBuilding("81");
//        pr.setExtensions("1");
//        pr.setApartment("52");
//
//        PersonCheckDao dao = new PersonCheckDao();
//        PersonResponse ps = dao.checkPerson(pr);
//        Assert.assertTrue(ps.isRegistered());
//        Assert.assertFalse(ps.isTemporal());
//    }
}
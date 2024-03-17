package City.Dao;

import City.Exceptions.CheckPersonException;
import City.domian.PersonRequest;
import City.domian.PersonResponse;
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
        pr.setExtension("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps1 = dao.checkPerson(pr);
        Assert.assertTrue(ps1.isRegistered());
        Assert.assertFalse(ps1.isTemporal());

        String s = buildString();
        System.out.println(s);

    }

    private String buildString() {
        String answer = "Hello";
        return answer;
    }

    @Test
    public void checkPerson2() throws CheckPersonException {

       PersonRequest pr = new PersonRequest();
       pr.setSurName("Васильева");
       pr.setGivenName("Ирина");
       pr.setPatronymic("Петровна");
       pr.setDateOfBirth(LocalDate.of(1997, 8, 21));
       pr.setStreetCode(1);
       pr.setBuilding("10");
       pr.setExtension("2");
       pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps = dao.checkPerson(pr);
        Assert.assertTrue(ps.isRegistered());
        Assert.assertFalse(ps.isTemporal());
    }
}
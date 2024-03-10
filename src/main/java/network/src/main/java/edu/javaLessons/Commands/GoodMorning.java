package network.src.main.java.edu.javaLessons.Commands;

public class GoodMorning  extends Greetable {
    @Override
    public String seyResponse(String userName) {
       return  "Good morning" + userName;
    }
}

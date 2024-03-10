package network.src.main.java.edu.javaLessons.Commands;

public class GoodEvening extends Greetable {
    @Override
    public String seyResponse(String userName) {
        return "Good Evening" + userName;
    }
}

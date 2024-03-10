package greet;

import interfaces.Greetable;

public class GoodMorning  extends Greetable {
    @Override
    public String seyResponse(String userName) {
       return  "Good morning " + userName;
    }
}

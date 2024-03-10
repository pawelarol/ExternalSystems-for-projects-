package greet;

import interfaces.Greetable;

public class GoodEvening extends Greetable {
    @Override
    public String seyResponse(String userName) {
        return "Good Evening " + userName;
    }
}

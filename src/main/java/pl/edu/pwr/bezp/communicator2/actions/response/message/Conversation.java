package pl.edu.pwr.bezp.communicator2.actions.response.message;

import java.util.ArrayList;

public class Conversation {
    public String name;
    public ArrayList<String> users;

    public Conversation(String name, ArrayList<String> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}

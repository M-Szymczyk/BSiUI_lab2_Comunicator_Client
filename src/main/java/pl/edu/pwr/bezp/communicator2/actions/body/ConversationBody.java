package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;

public class ConversationBody implements Body {
    private final ConversationNameBody name;
    private String[] users;

    public String getName() {
        return name.getConversation();
    }

    public void setName(String name) {
        this.name.setConversation(name);
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public ConversationBody(ConversationNameBody name, String[] users) {
        this.name = name;
        this.users = users;
    }
}

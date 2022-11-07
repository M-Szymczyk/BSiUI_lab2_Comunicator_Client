package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;
import java.util.Arrays;

public class ConversationCreatorBody implements Body {
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
    public ConversationCreatorBody(){
        try {
            name = new ConversationNameBody();
//            System.out.print("Wprowadz nazwe konwersacji: ");
//            name = reader.readLine();
            System.out.print("\nWprowadz nazwe uzytkownika: ");
            users = new String[]{reader.readLine()};
            System.out.println();
            //todo poprawic wybor z istniejacych uzytkownikow
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

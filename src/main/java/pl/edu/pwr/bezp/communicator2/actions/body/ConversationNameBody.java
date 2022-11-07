package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;

public class ConversationNameBody implements Body {
    private String conversation;

    public ConversationNameBody() {
        try {
            System.out.print("Wprowadz nazwe konwersacji: ");
            conversation = reader.readLine();
            System.out.println();
            //todo poprawic wybor z istniejacych konwersacji
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }
}
package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;

public class MessageBody implements Body {
    private String conversation;
    private String content;

    public MessageBody() {
        try {
            System.out.print("Wprowadz nazwe konwersacji: ");
            conversation = reader.readLine();
            System.out.print("\nWprowadz wiadomosc: ");
            content = reader.readLine();
            //todo dodac listowanie konwersacji wybor z ktorej z istniejacych konwersacji
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

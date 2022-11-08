package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;

public class ConversationNameBody implements Body {
    private String conversation;

    public ConversationNameBody(String conversation) {
        this.conversation = conversation;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }
}
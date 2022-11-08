package pl.edu.pwr.bezp.communicator2.actions.body;

public class MessageBody implements Body {
    private String conversation, content;

    public MessageBody(String conversation, String content) {
        this.conversation = conversation;
        this.content = content;
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

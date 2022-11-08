package pl.edu.pwr.bezp.communicator2.actions.response.message;

public class Message {
    public String author;
    public String dateTime;
    public String content;

    public Message(String author, String dateTime, String content) {
        this.author = author;
        this.dateTime = dateTime;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

package pl.edu.pwr.bezp.communicator2.actions.response;

import pl.edu.pwr.bezp.communicator2.actions.response.message.Message;

import java.util.List;

public interface ResponseReader {
    void unSuccessfulAction(String reason);
    void listMessagesFromConversations(List<Message> messages);

    void listUnreadMessages(List<Message> messages);

    void listConversations(List<String> namesOfConversations);

    void listUsersOfConversations(List<String> users);

    void listAvailableUsers(List<String> users);
}

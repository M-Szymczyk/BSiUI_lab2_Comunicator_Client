package pl.edu.pwr.bezp.communicator2.actions.response;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.response.message.Message;

import java.util.List;

@Component
public class TerminalOutput implements ResponseReader {
    @Override
    public void unSuccessfulAction(String reason) {
        System.out.println("Niepowodzenie wykonania operacji. Powod: "+reason);
    }

    @Override
    public void listMessagesFromConversations(List<Message> messages) {

    }

    @Override
    public void listUnreadMessages(List<Message> messages) {

    }

    @Override
    public void listConversations(List<String> namesOfConversations) {

    }

    @Override
    public void listUsersOfConversations(List<String> users) {

    }

    @Override
    public void listAvailableUsers(List<String> users) {
        System.out.println("Lista dostępnych klientów: ");
        System.out.println(users);
    }
}

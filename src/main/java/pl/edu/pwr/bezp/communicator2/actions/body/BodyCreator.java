package pl.edu.pwr.bezp.communicator2.actions.body;

import java.util.List;

public interface BodyCreator {
    LoginBody fillLoginBody();
    ConversationBody fillConversationCreatorBody(List<String> availableUsersName);
    ConversationNameBody fillConversationNameBody();
    MessageBody fillMessageBody(List<String> availableConversations);
}

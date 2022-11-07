package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.ConversationNameBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespGetConversationMessages;

@Component("getConversationMessages")
public class ActionGetConversationMessages extends AbstractAction {
    @Override
    RespAbstract run() {
        return new RespGetConversationMessages(communication(new RequestData("getConversationMessages",
                new ConversationNameBody())));
    }
}

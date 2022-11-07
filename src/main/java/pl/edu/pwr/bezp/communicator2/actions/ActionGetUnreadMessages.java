package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespGetMessages;

@Component("getMessages")
public class ActionGetUnreadMessages extends AbstractAction {
    @Override
    RespAbstract run() {
        return new RespGetMessages(communication(new RequestData("getMessages", null)));
    }
}

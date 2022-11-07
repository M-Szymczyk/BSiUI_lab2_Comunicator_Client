package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespListConversations;

@Component("listConversations")
public class ActionListConversations extends AbstractAction {

    @Override
    RespAbstract run() {
        return new RespListConversations(communication(new RequestData("listConversations", null)));
    }
}
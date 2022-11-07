package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespLogin;

@Component("listConversations")
public class ActionListUsers extends AbstractAction {
    @Override
    RespAbstract run() {
        return new RespLogin(communication(new RequestData("listConversations", null)));
    }
}
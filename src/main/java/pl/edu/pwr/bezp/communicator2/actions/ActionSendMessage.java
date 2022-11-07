package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.MessageBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;

@Component("message")
public class ActionSendMessage extends AbstractAction{
    @Override
    RespAbstract run() {
        return new RespAbstract(communication(new RequestData("message", new MessageBody()))) {
        };
    }
}

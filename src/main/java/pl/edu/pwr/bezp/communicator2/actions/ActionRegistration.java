package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.Body;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespRegistration;
import pl.edu.pwr.bezp.communicator2.client.CommunicatorClient;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("register")
public class ActionRegistration extends AbstractAction {

    public ActionRegistration(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run(CommunicatorClient communicatorClient) {
        return getRegister(bodyCreator.fillLoginBody());
    }
    @Override
    public RespAbstract run(Body body) {
        return getRegister(body);
    }

    private RespRegistration getRegister(Body body) {
        return new RespRegistration(communication(new RequestData("register", body)));
    }
}

package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.LoginBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespLogin;
import pl.edu.pwr.bezp.communicator2.client.SocketsConnectionLayer;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;

@Component("login")
public class ActionLogin extends AbstractAction {
    public ActionLogin(SocketsConnectionLayer connectionLayer, AES aes) {
        super(connectionLayer, aes);
    }

    @Override
    public RespAbstract run() {
        return new RespLogin(communication(new RequestData("login", new LoginBody())));
    }
}

package pl.edu.pwr.bezp.communicator2.actions;

import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.body.LoginBody;
import pl.edu.pwr.bezp.communicator2.actions.body.RequestData;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;
import pl.edu.pwr.bezp.communicator2.actions.response.RespLogin;

@Component("login")
public class ActionLogin  extends AbstractAction{

//    public ActionLogin(String login, String password) throws Exception {
//        super("login", makeBody(login,password));
//    }
//
//    private static JSONObject makeBody(String login, String password) throws Exception {
//        try {
//            JSONObject body = new JSONObject();
//            body.put("login", login);
//            body.put("password", password);
//            return body;
//        } catch (JSONException e) {
//            throw new Exception(e);
//        }
//    }

    @Override
    RespAbstract run() {
        return new RespLogin(communication(new RequestData("login", new LoginBody())));
    }
}

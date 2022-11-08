package pl.edu.pwr.bezp.communicator2.actions.response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RespListUsers extends RespAbstract {
    private final List<String> users = new ArrayList<>();

    public RespListUsers(String respText) {
        super(respText);
        try {
            var resp = new JSONObject(respText).getJSONObject("body").getJSONArray("users");
            for (int i = 0; i < resp.length(); i++) {
                users.add(resp.getString(i));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        responseReader.listAvailableUsers(getUsers());
    }

    public List<String> getUsers() {
        return users;
    }
}

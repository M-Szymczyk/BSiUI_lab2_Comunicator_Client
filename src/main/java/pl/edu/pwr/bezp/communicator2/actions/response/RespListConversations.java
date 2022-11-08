package pl.edu.pwr.bezp.communicator2.actions.response;

import org.json.JSONException;
import org.json.JSONObject;
import pl.edu.pwr.bezp.communicator2.actions.response.message.Conversation;

import java.util.ArrayList;

public class RespListConversations extends RespAbstract{
    private final ArrayList<Conversation> conversations = new ArrayList<>();

    public RespListConversations(String respText) {
        super(respText);
        try {
            var resp = new JSONObject(respText).getJSONObject("body").getJSONArray("conversations");
            for (int i = 0; i < resp.length(); i++) {
                var jsonObject = resp.getJSONObject(i);
                var tempUserList = new ArrayList<String>();
                if(jsonObject.getJSONArray("users").length()==0)
                    return;
                for (int j = 0; j < jsonObject.getJSONArray("users").length(); j++) {
                    tempUserList.add( jsonObject.getJSONArray("users").getString(0));
                }
                conversations.add(new Conversation(jsonObject.getString("name"),tempUserList));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }
}

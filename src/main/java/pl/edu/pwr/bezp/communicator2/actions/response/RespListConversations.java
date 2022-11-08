package pl.edu.pwr.bezp.communicator2.actions.response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class Conversation{
    public String name;
    public ArrayList<String> users;

    public Conversation(String name, ArrayList<String> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}

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
            System.out.println(conversations);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Conversation> getConversations() {
        return conversations;
    }
}

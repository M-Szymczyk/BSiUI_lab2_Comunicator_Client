package pl.edu.pwr.bezp.communicator2.actions.body;

import java.io.IOException;

public class LoginBody implements Body {
    private String login, password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBody(String login, String password) {
        this.login = login;
        this.password = password;
    }


}

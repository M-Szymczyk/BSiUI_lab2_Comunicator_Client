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

    public LoginBody() {
        try {
            System.out.println("Wprowadz login: ");
            login = reader.readLine();
            System.out.println("\nWprowadz haslo: ");
            password = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

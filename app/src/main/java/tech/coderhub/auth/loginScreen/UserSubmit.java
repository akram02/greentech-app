package tech.coderhub.auth.loginScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSubmit {

    @SerializedName("username")
    @Expose
    private String email;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    public UserSubmit(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserSubmit(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
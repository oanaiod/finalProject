package ObjectModel;

import javax.xml.bind.annotation.XmlElement;

public class AccountModel {
    private String username;
    private String password;

    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }



}

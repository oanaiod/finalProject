package ObjectModel;

import javax.xml.bind.annotation.XmlElement;

public class RegisterModel {
    private ObjectModel.AccountModel account;
    private String error;

    private String email;
    public RegisterModel() {
    }

    public RegisterModel(String username,String email, String password, String error) {
        ObjectModel.AccountModel accountM = new ObjectModel.AccountModel();
        accountM.setPassword(password);
        accountM.setEmail(email);
        accountM.setUsername(username);

        this.account = accountM;
        this.error = error;
    }

    public ObjectModel.AccountModel getAccount() {
        return account;
    }
    @XmlElement
    public void setAccount(ObjectModel.AccountModel account) {
        this.account = account;
    }

    public String getEmail(){return email;}
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
    public String getError() {
        return error;
    }
    @XmlElement
    public void setUserError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "account={username:" + account.getUsername() + ",password:" + account.getPassword() +
                "}, error='" + error + '\'' +
                '}';
    }
}

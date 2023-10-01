package ObjectModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModel {
    private ObjectModel.AccountModel account;
    private String userError;
    private String passwordError;

    public LoginModel() {
    }

    public LoginModel(String username, String password, String userError) {
        ObjectModel.AccountModel accountM = new ObjectModel.AccountModel();
        accountM.setPassword(password);
        accountM.setUsername(username);

        this.account = accountM;
        this.userError = userError;
        this.passwordError = passwordError;
    }

    public ObjectModel.AccountModel getAccount() {
        return account;
    }

    @XmlElement
    public void setAccount(ObjectModel.AccountModel account) {
        this.account = account;
    }

    public String getUserError() {
        return userError;
    }

    @XmlElement
    public void setUserError(String userError) {
        this.userError = userError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    @XmlElement
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "account={username:" + account.getUsername() + ",password:" + account.getPassword() +
                "}, userError='" + userError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                '}';
    }
}

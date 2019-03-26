package examples.struts;

import org.apache.struts.action.ActionForm;

public class InputForm extends ActionForm {
	
    private String username = "";
    private String password = "";

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

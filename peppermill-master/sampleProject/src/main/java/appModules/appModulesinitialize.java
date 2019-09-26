package appModules;

import com.uiautomator.peppermill.Peppermillinitialize;

public class appModulesinitialize {
    private Peppermillinitialize init;
    public Login login;
    public Globals globals;

    public appModulesinitialize(Peppermillinitialize init){
        this.init = init;
        this.login = new Login(init);

    }
}

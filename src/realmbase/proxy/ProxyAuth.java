package realmbase.proxy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuth extends Authenticator {
    
    private PasswordAuthentication auth;
    
    public ProxyAuth(String strUserName, String strPasswd) {
        auth = new PasswordAuthentication(strUserName, strPasswd.toCharArray());
    }
    
    protected PasswordAuthentication getPasswordAuthentication() {
        return auth;
    }
}
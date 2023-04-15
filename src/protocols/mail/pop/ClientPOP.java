package protocols.mail.pop;

import protocols.mail.UserPackaging;

/**
 *
 * @author dogi_
 */

public class ClientPOP{
    
    public String server_smtp;
    public UserPackaging sending_user_package=null;
    
    public ClientPOP(){
        
    }
    
    public void connect(String server_smtp){
        this.server_smtp=server_smtp;
    }
    
    public void addUser(String address, String password){
        this.sending_user_package=new UserPackaging(address,password);
    }
    
    public UserPackaging getUser(){
        return this.sending_user_package;
    }
    
}

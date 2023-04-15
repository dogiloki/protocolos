package protocols.mail.pop;

import protocols.mail.UserPackaging;

/**
 *
 * @author dogi_
 */

public class ClientPOP{
    
    public String server_pop;
    public UserPackaging sending_user_package=null;
    
    public ClientPOP(){
        
    }
    
    public void connect(String server_pop){
        this.server_pop=server_pop;
    }
    
    public void addUser(String address, String password){
        this.sending_user_package=new UserPackaging(address,password);
    }
    
    public UserPackaging getUser(){
        return this.sending_user_package;
    }
    
}

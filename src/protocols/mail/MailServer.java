package protocols.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class MailServer{
    
    public Map<String,MailUser> users=new HashMap<>();
    public List<Mail> mails=new ArrayList<>();
    
    public MailServer(){
        
    }
    
    public boolean auth(String mail, String password){
        MailUser user=this.users.get(mail);
        if(user==null){
            return false;
        }
        if(!user.password.equals(password)){
            return false;
        }
        return true;
    }
    
}

package protocols.mail;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Mail{
    
    public String mail_sender;
    public String mail_recipient;
    public String subject;
    public String body;
    public Map<Integer,byte[]> attachments=new HashMap<>();
    
    public Mail(){
        
    }
    
    
}

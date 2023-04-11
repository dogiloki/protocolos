package protocols.mail;

import java.util.HashMap;
import java.util.Map;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class Mail extends Entity{
    
    public String mail_sender;
    public String mail_recipient;
    public String subject;
    public String body;
    public Map<Integer,byte[]> attachments=new HashMap<>();
    
    public Mail(){
        
    }
    
    
}

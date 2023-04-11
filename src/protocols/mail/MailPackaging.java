package protocols.mail;

import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class MailPackaging extends Entity{
    
    public String mail_address;
    public String password;
    public Mail mail;
    
    public MailPackaging(String mail_address, String password, Mail mail){
        this.mail_address=mail_address;
        this.password=password;
        this.mail=mail;
        
    }
    
}

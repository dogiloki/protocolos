package protocols.mail;

/**
 *
 * @author dogi_
 */

public class MailPackaging{
    
    public String mail_address;
    public String password;
    public Mail mail;
    
    public MailPackaging(String mail_address, String password, Mail mail){
        this.mail_address=mail_address;
        this.password=password;
        this.mail=mail;
        
    }
    
}

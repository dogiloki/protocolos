package protocols.mail;

/**
 *
 * @author dogi_
 */

public class MailPackaging{
    
    public String mail_address;
    public String password;
    public Mail mail;
    
    public MailPackaging(String password, Mail mail){
        this.mail_address=mail.mail_sender;
        this.password=password;
        this.mail=mail;
        
    }
    
}

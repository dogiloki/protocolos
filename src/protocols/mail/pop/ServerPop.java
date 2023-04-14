package protocols.mail.pop;

import protocols.mail.Mail;
import protocols.mail.MailServer;

/**
 *
 * @author dogi_
 */

public class ServerPop{
 
    public MailServer server_mail;
    
    public ServerPop(){
        
    }
    
    public void connect(MailServer server_mail){
        this.server_mail=server_mail;
    }
    
    public boolean auth(String mail_address, String password){
        return this.server_mail.auth(mail_address,password);
    }
    
    public void getMailsSender(String mail){
        this.server_mail.getMailsSender(mail);
    }
    
    public void getMailsRecipient(String mail){
        this.server_mail.getMailsRecipient(mail);
    }
    
}

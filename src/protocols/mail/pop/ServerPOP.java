package protocols.mail.pop;

import java.util.List;
import protocols.mail.Mail;
import protocols.mail.MailServer;

/**
 *
 * @author dogi_
 */

public class ServerPOP{
 
    public MailServer server_mail;
    
    public ServerPOP(){
        
    }
    
    public void connect(MailServer server_mail){
        this.server_mail=server_mail;
    }
    
    public boolean auth(String mail_address, String password){
        return this.server_mail.auth(mail_address,password);
    }
    
    public List<Mail> getMailsSender(String address){
        return this.server_mail.getMailsSender(address);
    }
    
    public List<Mail> getMailsRecipient(String address){
        return this.server_mail.getMailsRecipient(address);
    }
    
}

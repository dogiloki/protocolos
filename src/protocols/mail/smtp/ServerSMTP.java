package protocols.mail.smtp;

import protocols.mail.Mail;
import protocols.mail.MailServer;

/**
 *
 * @author dogi_
 */

public class ServerSMTP extends SMTP{
    
    public MailServer server_mail;
    
    public ServerSMTP(){
        
    }
    
    public void connect(MailServer server_mail){
        this.server_mail=server_mail;
    }
    
    public boolean auth(String mail_address, String password){
        return this.server_mail.auth(mail_address,password);
    }
    
    public void addMail(Mail mail){
        this.server_mail.mails.add(mail);
    }
    
}


package protocols.mail.smtp;

import protocols.mail.Mail;
import protocols.mail.MailPackaging;

/**
 *
 * @author dogi_
 */

public class ClientSMTP extends SMTP{
    
    public String server_smtp;
    public MailPackaging sending_mail_package=null;
    
    public ClientSMTP(){
        
    }
    
    public void connect(String server_smtp){
        this.server_smtp=server_smtp;
    }
    
    public void addMail(String password, Mail mail){
        this.sending_mail_package=new MailPackaging(password,mail);
    }
    
    public MailPackaging getMail(){
        return this.sending_mail_package;
    }
    
}

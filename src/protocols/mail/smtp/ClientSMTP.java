
package protocols.mail.smtp;

import java.util.ArrayList;
import java.util.List;
import protocols.mail.Mail;
import protocols.mail.MailPackaging;

/**
 *
 * @author dogi_
 */

public class ClientSMTP extends SMTP{
    
    public ServerSMTP server_smtp;
    public List<MailPackaging> sending_mail_packages=new ArrayList<>();
    
    public ClientSMTP(){
        
    }
    
    public void connect(ServerSMTP server_smtp){
        this.server_smtp=server_smtp;
    }
    
    public boolean addMail(String mail_address, String password, Mail mail){
        this.sending_mail_packages.add(new MailPackaging(mail_address,password,mail));
        return true;
    }
    
}

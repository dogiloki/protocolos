package protocols.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class MailServer{
    
    private Map<String,MailUser> users=new HashMap<>();
    private List<Mail> mails=new ArrayList<>();
    private Map<String,Mail> user_sender_mails=new HashMap<>();
    private Map<String,Mail> user_recipient_mails=new HashMap<>();
    
    public MailServer(){
        this.setMailUser();
    }
    
    public boolean auth(String mail, String password){
        MailUser user=this.users.get(mail);
        if(user==null){
            return false;
        }
        if(!user.password.equals(password)){
            return false;
        }
        return true;
    }
    
    public boolean exists(String mail){
        return this.users.get(mail)!=null;
    }
    
    public void addMail(Mail mail){
        this.user_sender_mails.put(mail.mail_sender,mail);
        this.user_recipient_mails.put(mail.mail_recipient,mail);
        this.mails.add(mail);
    }
    
    public void getMailsSender(String mail){
        this.user_sender_mails.get(mail);
    }
    
    public void getMailsRecipient(String mail){
        this.user_recipient_mails.get(mail);
    }
    
    public void setMailUser(){
        this.users.put("julio@example.com",new MailUser("Julio","Villanueva","julio@example.com","123"));
        this.users.put("juan@example.com",new MailUser("Juan","Lopéz","juan@example.com","123"));
        this.users.put("martha@example.com",new MailUser("Matha","Rivero","martha@example.com","123"));
        this.users.put("maria@example.com",new MailUser("Maria","Camacho","maria@example.com","123"));
    }
    
}

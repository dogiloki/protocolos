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
        this.mails.add(mail);
    }
    
    public List<Mail> getMailsSender(String address){
        List<Mail> mails=new ArrayList<>();
        for(Mail mail:this.mails){
            if(mail.mail_sender.equals(address)){
                mails.add(mail);
            }
        }
        return mails;
    }
    
    public List<Mail> getMailsRecipient(String address){
        List<Mail> mails=new ArrayList<>();
        for(Mail mail:this.mails){
            if(mail.mail_recipient.equals(address)){
                mails.add(mail);
            }
        }
        return mails;
    }
    
    public Map<String,MailUser> getUsers(){
        return this.users;
    }
    
    public void setMailUser(){
        this.users.put("julio@example.com",new MailUser("Julio","Villanueva","julio@example.com","123"));
        this.users.put("juan@example.com",new MailUser("Juan","Lopéz","juan@example.com","123"));
        this.users.put("martha@example.com",new MailUser("Matha","Rivero","martha@example.com","123"));
        this.users.put("maria@example.com",new MailUser("Maria","Camacho","maria@example.com","123"));
    }
    
}

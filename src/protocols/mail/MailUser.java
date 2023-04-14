package protocols.mail;

/**
 *
 * @author dogi_
 */

public class MailUser{
    
    public String name;
    public String surname;
    public String address;
    public String password;
    
    public MailUser(String name, String surname, String address, String password){
        this.name=name;
        this.surname=surname;
        this.address=address;
        this.password=password;
    }
    
}

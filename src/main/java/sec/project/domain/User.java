package sec.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private boolean admin;

    @OneToMany
    private List<Contact> contacts = new ArrayList<>();
    
    public User() {
    }

    public User(String username, String password, boolean admin) {
        this();
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    public List getContacts() {
        return contacts;
    }
    
    @Override
    protected void setId(Long id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
   
}

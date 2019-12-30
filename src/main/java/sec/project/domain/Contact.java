/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mmohamud
 */

@Entity
public class Contact extends AbstractPersistable<Long>{
    private String name;
    private String number;

    @ManyToOne
    private User user;
    
    public Contact() {
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }
       
    @Override
    protected void setId(Long id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

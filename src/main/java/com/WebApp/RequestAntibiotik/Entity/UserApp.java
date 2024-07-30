
package com.WebApp.RequestAntibiotik.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="app_user")
public class UserApp {
     
        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

        @Column(name="first_name")
	private String firstName;

        @Column(name="last_name")
	private String lastName;

        @Column(name="email")
	private String email;

        @Column(name="password")
	private String password;

        @Column(name="role")
	private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

  
        
        
        
}

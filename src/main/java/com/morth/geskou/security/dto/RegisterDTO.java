package com.morth.geskou.security.dto;

public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
   
    public RegisterDTO() {
    }
    public RegisterDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
    
    // Aojouter les autres champs de l'utilisateur si besoin
    // Ce dto sert à récupérer les données de l'utilisateur lors de son inscription, username, passeword, email, etc...
    // Il est utilisé dans le controller pour récupérer les données de l'utilisateur

}

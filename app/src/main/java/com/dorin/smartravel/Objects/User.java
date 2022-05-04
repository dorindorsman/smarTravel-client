package com.dorin.smartravel.Objects;

public class User {

    private String avatar;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String yearBirth;
    private String gender;
    private String role;
    private enum ROLE {MANAGER,PLAYER,ADMIN};

    public User(String avatar, String firstName, String lastName, String userName, String email, String yearBirth, String gender) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.yearBirth = yearBirth;
        this.gender = gender;
        this.role= "PLAYER";
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getYearBirth() {
        return yearBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }


}

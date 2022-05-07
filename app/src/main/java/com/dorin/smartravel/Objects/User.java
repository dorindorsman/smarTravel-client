package com.dorin.smartravel.Objects;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String avatar;
    private String email;
    private String role;
    private String username;
    private String firstName;
    private String lastName;
    private String yearBirth;
    private String gender;
    private String domain;
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
        this.domain="";
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

    public String getDomain() {
        return domain;
    }

    public User setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearBirth='" + yearBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    public JSONObject convertToJson(){
        JSONObject userJson=new JSONObject();
        try {
            userJson.put("email",this.getEmail());
            userJson.put("role",this.getRole());
            userJson.put("username",this.getUsername());
            userJson.put("avatar",this.getAvatar());
        }catch (JSONException e){
            Log.d("ptt",e.getMessage());
        }

        return userJson;
    }


}

package com.snake.shaadiproject.data;

public class Results {
    private String gender;

    private Name name;

    private Location location;

    private String email;

    private Login login;

    private Dob dob;

    private Registered registered;

    private String phone;

    private String cell;

    private Id id;

    private Picture picture;

    private String nat;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return this.name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Dob getDob() {
        return this.dob;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public Registered getRegistered() {
        return this.registered;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCell() {
        return this.cell;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Id getId() {
        return this.id;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Picture getPicture() {
        return this.picture;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getNat() {
        return this.nat;
    }

    public Results(String gender, Name name, Location location, Dob dob, Picture picture) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.dob = dob;
        this.picture = picture;
    }
}

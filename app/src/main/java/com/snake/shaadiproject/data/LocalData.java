package com.snake.shaadiproject.data;

public class LocalData {
    private String name;
    private String gender;
    private String image;
    private String age;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalData(String name, String gender, String image, String age, String location) {
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.age = age;
        this.location = location;
    }
}

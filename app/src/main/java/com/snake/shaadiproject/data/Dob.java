package com.snake.shaadiproject.data;
public class Dob
{
    private String date;

    private long age;

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setAge(int age){
        this.age = age;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Dob(String date) {
        this.date = date;
    }
}
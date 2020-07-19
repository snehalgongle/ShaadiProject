package com.snake.shaadiproject.data;
public class Name
{
    private String title;

    private String first;

    private String last;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setFirst(String first){
        this.first = first;
    }
    public String getFirst(){
        return this.first;
    }
    public void setLast(String last){
        this.last = last;
    }
    public String getLast(){
        return this.last;
    }

    public Name(String first) {
        this.first = first;
    }
}
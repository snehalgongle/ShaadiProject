package com.snake.shaadiproject.data;
public class Picture
{
    private String large;

    private String medium;

    private String thumbnail;

    public void setLarge(String large){
        this.large = large;
    }
    public String getLarge(){
        return this.large;
    }
    public void setMedium(String medium){
        this.medium = medium;
    }
    public String getMedium(){
        return this.medium;
    }
    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }
    public String getThumbnail(){
        return this.thumbnail;
    }

    public Picture(String large) {
        this.large = large;
    }
}

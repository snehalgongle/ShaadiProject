package com.snake.shaadiproject.data;
public class Timezone
{
    private String offset;

    private String description;

    public void setOffset(String offset){
        this.offset = offset;
    }
    public String getOffset(){
        return this.offset;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
}
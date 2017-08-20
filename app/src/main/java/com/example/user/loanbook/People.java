package com.example.user.loanbook;

/**
 * Created by USER on 8/19/2017.
 */

public class People {
    public int id;
    public String name;
    public String number;
    public String type;
    public String date;

    public People(){

    }

    public People(int id,String name, String number, String type, String date) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

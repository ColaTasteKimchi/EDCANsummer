package com.example.edcansummer;

public class MemorModel {
    private String text, time, email;

    public MemorModel(){
    }

    public MemorModel(String text, String time, String email) {
        this.text = text;
        this.time = time;
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

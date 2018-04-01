package com.example.sihagriculture;

public class Message {
    private String message;
    private Type type;
    private String aadhar_id;


    private String username;

    public Message(String message, Type type, String username, String aadhar_id) {
        this.message = message;
        this.username = username;
        this.type = type;
        this.aadhar_id = aadhar_id;
    }

    public Type getType() {
        return type;
    }

    public String getAadhar_id() {
        return aadhar_id;
    }

    public void setAadhar_id(String aadhar_id) {
        this.aadhar_id = aadhar_id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Type {
        SENT, RECEIVED
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

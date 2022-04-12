package com.example.demo.model;

public class ChatMessage {

    private String content;

    private String username;

    public ChatMessage() {
    }

    public ChatMessage(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

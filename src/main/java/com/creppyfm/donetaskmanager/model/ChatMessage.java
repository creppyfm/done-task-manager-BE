package com.creppyfm.donetaskmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {
    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;

    // Default constructor
    public ChatMessage() {
    }

    // Constructor with parameters
    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getter and setter methods
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

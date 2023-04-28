package com.creppyfm.donetaskmanager.model;

public record OpenAIRequest(String model, String prompt, int temperature, int max_tokens) {

}

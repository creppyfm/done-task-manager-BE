package com.creppyfm.donetaskmanager.model;

public record OpenAIResponse(
        String id,
        String object,
        int created,
        String model,
        ResponseChoice[] choices,
        ResponseUsage usage
) {
}

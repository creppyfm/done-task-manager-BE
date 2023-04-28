package com.creppyfm.donetaskmanager.model;

public record ResponseUsage(int prompt_tokens, int completion_tokens, int total_tokens) {
}

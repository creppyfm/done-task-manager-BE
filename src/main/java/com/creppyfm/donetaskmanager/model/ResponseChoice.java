package com.creppyfm.donetaskmanager.model;

public record ResponseChoice(
        String text,
        int index,
        Object logprobs,
        String finish_reason
) {
}

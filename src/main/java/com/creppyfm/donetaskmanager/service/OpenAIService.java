package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.Task;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO: implement 'TaskList' Record, override 'toString()', and pass that string as prompt
@Service
public class OpenAIService {
    private final OkHttpClient httpClient;
    private final Gson gson;

    public OpenAIService() {
        httpClient = new OkHttpClient.Builder().build();
        gson = new Gson();
    }

    public List<Task> categorizeTasks(List<Task> tasks) {
        // Implement OpenAI API call to categorize tasks

        List<Task> taskList = new ArrayList<>();
        return taskList;
    }

    public List<Task> prioritizeTasks(List<Task> tasks) {
        // Implement OpenAI API call to prioritize tasks

        List<Task> taskList = new ArrayList<>();
        return taskList;
    }

    //TODO: test requests to api directly
    private String callOpenAiApi(String prompt) throws IOException {
        String apiKey = System.getenv("OPENAI_API_KEY");
        String apiUrl = "https://api.openai.com/v1/completions";

        String jsonBody = gson.toJson(new OpenAiRequest("text-davinci-003", prompt, 10)); // Create the JSON request body

        Request request = new Request.Builder() // Prepare the API request
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
                .build();

        //TODO: handle possible null value when deserializing response
        try (Response response = httpClient.newCall(request).execute()) { // Make the API call and process the response
            if (response.isSuccessful() && response.body() != null) {
                OpenAiResponse apiResponse = gson.fromJson(response.body().string(), OpenAiResponse.class); // Deserialize the JSON response
                return apiResponse.choices.get(0).text.trim(); // Extract the first result from the API response
            }
        }

        return null;
    }

    //TODO: generate getters and setters?
    static class OpenAiRequest {
        String model;
        String prompt;
        int max_tokens;

        OpenAiRequest(String model, String prompt, int max_tokens) {
            this.model = model;
            this.prompt = prompt;
            this.max_tokens = max_tokens;
        }
    }

    //TODO: generate constructor. Possibly getters and setters?
    static class OpenAiResponse {
        List<Completion> choices;

    }

    //TODO: generate constructor. Possibly getters and setters?
    static class Completion {
        String id;
        String text;

    }
}

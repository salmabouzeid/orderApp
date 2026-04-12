import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIFacade {

    private static final String ENDPOINT = "https://60099-m1xc2jq0-australiaeast.openai.azure.com/";
    private static final String DEPLOYMENT_NAME = "gpt-5-mini-vanilson";
    private static final String API_KEY = System.getenv("AZURE_API_KEY");
    private static final String API_VERSION = "2024-02-15-preview";

    public String getSuggestion(String orderDetails) {
        try {
            if (isBlank(API_KEY)) {
                return "Missing Azure API key. Set AZURE_API_KEY first.";
            }

            String response = callAPI(buildPrompt(orderDetails));
            return parseResponse(response);

        } catch (Exception e) {
            return "AI analysis is currently unavailable.\nReason: " + e.getMessage();
        }
    }

    private String buildPrompt(String orderDetails) {
        return "Estimate calories, protein, carbs, fat, a better option, and one health tip for: " + orderDetails;
    }

    private String callAPI(String prompt) throws IOException, InterruptedException {
        String url = ENDPOINT
                + "openai/deployments/"
                + DEPLOYMENT_NAME
                + "/chat/completions?api-version="
                + API_VERSION;

        String body = """
                {
                  "messages": [
                    {
                      "role": "developer",
                      "content": "Reply in 6 short lines only: Estimated Calories, Protein, Carbs, Fat, Better Option, Health Tip."
                    },
                    {
                      "role": "user",
                      "content": %s
                    }
                  ],
                  "max_completion_tokens": 1200,
                  "reasoning_effort": "minimal"
                }
                """.formatted(jsonEscape(prompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("RAW AZURE RESPONSE:\n" + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("Azure API error: " + response.statusCode() + "\n" + response.body());
        }

        return response.body();
    }

    private String parseResponse(String response) {
        String messageMarker = "\"message\":";
        int messageIndex = response.indexOf(messageMarker);

        if (messageIndex == -1) {
            return "Could not find message in AI response.\nRaw response:\n" + response;
        }

        String contentMarker = "\"content\":\"";
        int contentIndex = response.indexOf(contentMarker, messageIndex);

        if (contentIndex == -1) {
            return "Could not find content in AI response.\nRaw response:\n" + response;
        }

        int start = contentIndex + contentMarker.length();
        int end = start;
        boolean escaped = false;

        while (end < response.length()) {
            char c = response.charAt(end);

            if (c == '"' && !escaped) {
                break;
            }

            if (c == '\\' && !escaped) {
                escaped = true;
            } else {
                escaped = false;
            }

            end++;
        }

        String content = response.substring(start, end)
                .replace("\\n", "\n")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .trim();

        if (content.isEmpty()) {
            return "AI returned empty text again. Raise max_completion_tokens to 2000.";
        }

        return content;
    }

    private String jsonEscape(String text) {
        return "\"" + text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "") + "\"";
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
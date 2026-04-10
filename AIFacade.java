public class AIFacade {

    public String getSuggestion(String orderDetails) {
        String prompt = buildPrompt(orderDetails);
        String response = callAPI(prompt);
        return parseResponse(response);
    }

    private String buildPrompt(String orderDetails) {
        return "Suggest extra food for this order: " + orderDetails;
    }

    private String callAPI(String prompt) {
        //  Azure OpenAI code
        return "AI raw response"; // replace later
    }

    private String parseResponse(String response) {
        return response; // simplify for now
    }
}
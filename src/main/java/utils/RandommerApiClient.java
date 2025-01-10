package utils;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//This class to create the data that needed in the test cases
public class RandommerApiClient {

    private static final String API_KEY = "fa10596489964cf99e2a2f332706422d";

    //Hit the Firstname endpoint and get it from the response
    public static String fetchRandomFirstName() throws IOException, InterruptedException {
        String apiUrl = "https://randommer.io/api/Name?nameType=firstname&quantity=1";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-Api-Key", API_KEY)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        return response.body().replaceAll("[\\[\\]\"]", "");
    }

    //Hit the SocialNumber endpoint and get it from the response
    public static String fetchRandomPostcode() throws IOException, InterruptedException {
        String apiUrl = "https://randommer.io/api/SocialNumber";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-Api-Key", API_KEY)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().replaceAll("[\\[\\]\"]", "");
    }
}
package com.global.translator.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.JsonArray;

import java.io.IOException;

public class GoogleBooksApiExample {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "&key=";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String query = "rich dad poor dad";
        try {
            String jsonResponse = searchBooks(query);
            parseAndDisplayBooks(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String searchBooks(String query) throws IOException {
        Request request = new Request.Builder()
                .url(API_URL + query + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    private static void parseAndDisplayBooks(String jsonResponse) {
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray items = jsonObject.getAsJsonArray("items");

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.get(i).getAsJsonObject();
                JsonObject volumeInfo = item.getAsJsonObject("volumeInfo");

                String title = volumeInfo.get("title").getAsString();
                String authors = volumeInfo.has("authors") ? volumeInfo.get("authors").toString() : "N/A";
                String publisher = volumeInfo.has("publisher") ? volumeInfo.get("publisher").getAsString() : "N/A";
                String buy = volumeInfo.has("buyLink") ? volumeInfo.get("buyLink").toString() : "N/A";
                
                
                System.out.println("Title: " + title);
                System.out.println("Authors: " + authors);
                System.out.println("Publisher: " + publisher);
                System.out.println("buy: " + buy);
                System.out.println();
            }
        } else {
            System.out.println("No results found.");
        }
    }
}

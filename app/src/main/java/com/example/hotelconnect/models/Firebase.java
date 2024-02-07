package com.example.hotelconnect.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Firebase {

    private static final String FCM_ENDPOINT = "https://fcm.googleapis.com/fcm/send";
    private final String serverKey;

    private static Firebase instance;

    private Firebase(String serverKey) {
        this.serverKey = serverKey;
    }

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase("AAAAaTPfy_0:APA91bGwqfLhmhBVKA9o7yNfimk8iGS1o_7uWK3T5isEkSM2r86yd4J4Mdq_z9toxsaKYX_2L-aG5z36nWtlttuelXdkQCu9hqeurCgU5kKGJPEgKHE3bDWus7sdgGZa3GqJItf38cKA");
        }
        return instance;
    }

    public CompletableFuture<Void> sendToTopicAsync(String topic, String title, String body) {
        return CompletableFuture.runAsync(() -> {
            try {
                sendToTopic(topic, title, body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendToTopic(String topic, String title, String body) throws IOException {
        OkHttpClient client = new OkHttpClient();

        JsonObject payload = new JsonObject();
        payload.addProperty("to", "/topics/" + topic);

        JsonObject notification = new JsonObject();
        notification.addProperty("title", title);
        notification.addProperty("body", body);

        payload.add("notification", notification);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), payload.toString());

        Request request = new Request.Builder()
                .url(FCM_ENDPOINT)
                .addHeader("Authorization", "key=" + serverKey)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Message sent successfully
                System.out.println("FCM message sent successfully to topic " + topic + "!");
            } else {
                // Handle error
                System.out.println("Error sending FCM message. Status code: " + response.code());
            }
        }
    }
}

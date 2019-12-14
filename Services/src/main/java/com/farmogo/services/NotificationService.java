package com.farmogo.services;

import org.primefaces.json.JSONObject;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Stateless
public class NotificationService {
    private final String SERVER_KEY = "AAAAS_nt9oA:APA91bH4m4cYYwVO9J5cepIcXu6isl0djoTe5N7bDXdVca9TwbNTmA2wCnABYP6xpKHVfIMrTHC6MutMyKD1VT34vSIVhzRm_uAYweWjzVj0gGl5aysuKEnoxNLzDbS4HCHFeLHI6iUT";
    private final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public static final String ALL_TOPIC = "all";

    public String sendNotificationToTopic(String title, String body, String topic) throws Exception {

        JSONObject json = new JSONObject();
        JSONObject msg = new JSONObject();

        msg.put("title", title);
        msg.put("body", body);
        json.put("to", "/topics/" + topic);
        json.put("notification", msg);
        json.put("data", msg);

        return sendPushNotification(json);
    }


    private String sendPushNotification(JSONObject json) throws Exception {
        URL url = new URL(API_URL_FCM);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);

        try {
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }

            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
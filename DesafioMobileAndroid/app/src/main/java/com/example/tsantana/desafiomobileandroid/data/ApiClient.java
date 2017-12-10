package com.example.tsantana.desafiomobileandroid.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tsantana on 06/12/2017.
 */

public class ApiClient {
    private String url;

    public ApiClient(String url){
        this.url = url;
    }

    public String getJson() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            StringBuffer out = new StringBuffer();

            while((linha = reader.readLine()) != null) {
                out.append(linha);
                out.append("\n");
            }

            return out.toString();
        }catch (Exception e) {
            e.printStackTrace();

            if(connection != null){
                connection.disconnect();
            }

            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return "";
        }

    }



}

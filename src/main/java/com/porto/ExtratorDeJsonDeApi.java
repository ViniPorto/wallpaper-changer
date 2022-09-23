package com.porto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ExtratorDeJsonDeApi {
    
    public String extrairJson(String url){
        try{
            return HttpClient
            .newHttpClient()
            .send(HttpRequest.newBuilder(URI.create(url))
            .GET()
            .build(), BodyHandlers.ofString())
            .body();
        }catch(IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }
}
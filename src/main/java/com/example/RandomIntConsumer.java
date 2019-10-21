package com.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.Gson;

public class RandomIntConsumer
{
    private String endPoint="http://www2.freefarm.se/cgi-bin/randomint.cgi";
    private Integer randomInt=null;

    public RandomIntConsumer() {
    }

    public RandomIntConsumer(String endPoint) {
        this.endPoint = endPoint;
    }

    public static void main(String[] args ) {
        new RandomIntConsumer().callGetRandomInt();
    }
    public void callGetRandomInt() {
        try {
            HttpResponse<JsonNode> jsonResponse
                    = Unirest.get(endPoint).asJson();
            String response = jsonResponse.getBody().toString();
            Gson gson=new Gson();
            RandomIntDTO randomIntDTO = gson.fromJson(response, RandomIntDTO.class);
            randomInt=randomIntDTO.getRandomint();
            System.out.println("Random int="+randomInt);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public Integer getRandomInt() {
        return randomInt;
    }
}

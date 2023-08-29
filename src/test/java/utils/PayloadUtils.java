package utils;

import org.junit.Test;

public class PayloadUtils {


    public static String getPetPayload(int petId ,String petName){

        String petPayload = "{\n" +
                "  \"id\": "+petId+",\n" +  // delete hard code id and provide "+petId+"
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"supattra\"\n" +
                "  },\n" +
                "  \"name\": \""+petName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://s3.amazon.com\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    \n" +
                "  ],\n" +
                "  \"status\": \"sdet doggie\"\n" +
                "}";
        return petPayload;
    }

    public static String getSlackPayload(String message){

        String slackPayload ="\n" +
                "{\n" +
                "  \"channel\": \"C05H5S8A50V\",\n" +
                "  \"text\": \"Supattra:"+message+"\"\n" +
                "}";
        return slackPayload;


    }

    public static String getAirPortPayload(String originAirport, String destAirport){

        String airPortPayload = "{\n" +
                "    \"from\": \""+originAirport+"\",\n" +
                "    \"to\": \""+destAirport+"\"\n" +
                "}";
        return airPortPayload;

    }
}

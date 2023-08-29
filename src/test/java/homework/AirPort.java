package homework;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.AirportPojo;
import utils.PayloadUtils;

import java.util.*;

public class AirPort {
    @Test
    public void airPortTest() {
        /*
        Homework:
    - Calculate largest distance between airports,

     that are listed in a response of https://airportgap.dev-tester.com/api/airports API

    - TO DO so, you will need to retrieve airport IDs form https://airportgap.dev-tester.com/api/airports API

     and pass it to https://airportgap.dev-tester.com/api/airports/distance API.
         */

//
//        Map<String, Double> distanceBetweenAirPort = new HashMap<>();
//        RestAssured.baseURI = "https://airportgap.dev-tester.com/api/airports";
//        Response response = RestAssured.given()
//                .accept(ContentType.JSON)
//                .header("Authorization", "Bearer JrUQ9qQ9hbPM13r57KciPWxG")
//                .when().get()
//                .then().statusCode(200)
//                .extract().response();
//        AirportPojo airportPOJO = response.as(AirportPojo.class);
//
//        for (int i = 0; i < airportPOJO.getData().size() - 1; i++) {
//            RestAssured.baseURI = "https://airportgap.dev-tester.com";
//            RestAssured.basePath = "api/airports/distance";
//            Response responsePost = RestAssured.given()
//                    .accept(ContentType.JSON)
//                    .contentType(ContentType.JSON)
//                    .header("Authorization", "Bearer JrUQ9qQ9hbPM13r57KciPWxG")
//                    .body(PayloadUtils.getAirPortPayload(airportPOJO.getData().get(i).get("id").toString(), airportPOJO.getData().get(i + 1).get("id").toString()))
//                    .when().post()
//                    .then().statusCode(200)
//                    .extract().response();
//            AirportPOJO paredRes = responsePost.as(AirportPOJO.class);
//            distanceBetweenAirPort.put(airportPOJO.getData().get(i).get("id").toString() + " to " + airportPOJO.getData().get(i + 1).get("id").toString()
//                    , paredRes.getData().getAttributes().getMiles());
//
//        }
//        String airPorts = "";
//        Double dis = 1.0;
//        for (Map.Entry<String, Double> id : distanceBetweenAirPort.entrySet()) {
//
//            if (id.getValue() > dis) {
//                dis = id.getValue();
//                airPorts = id.getKey();
//            }
//
//        }
//        System.out.println(airPorts + " have the longest distance  between them " + dis);
//
//
//    }
    }
}

package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cat {

    @Test
    public void CountCatFact() {
        /*
        Sent a get request to this endpoint and deserialize the response and

        validate that you/re getting 100 facts about cats
         */

        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";
        Response response = RestAssured.given().accept("application/json")
                .queryParam("limit", 100)
                .when().get().then().statusCode(200).log().body().extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {

        });

        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResponse.get("data");
        Integer expectedFactNumber = 100;
        Integer actualFactNumber = data.size();
        Assert.assertEquals(expectedFactNumber, actualFactNumber);


    }


}

package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class FakeStore {
    @Test
    public void FakeStore(){

        RestAssured.baseURI="https://fakestoreapi.com/products/1";
        Response response = RestAssured.given().accept("application/json")
                .when().get().then().statusCode(200).log().body().extract()
                .response();

        Map<String,Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(deserializeResponse);
        double price = (double) deserializeResponse.get("price");
        Double price2 = (Double) deserializeResponse.get("price");

        Assert.assertEquals(109.95,price,0);
        Assert.assertEquals(109.95,price2,0);//use delta on Double
        Assert.assertEquals(109.95,deserializeResponse.get("price"));

        Map<String,Object> ratingMap = (Map<String, Object>) deserializeResponse.get("rating");

         /*
          "rating": {
        "rate": 3.9,
        "count": 120
         }
          */
        Double rate = (Double) ratingMap.get("rate");
        Integer count = (Integer) ratingMap.get("count");
        Assert.assertTrue(rate== 3.9);
        Assert.assertTrue(count==120);
    }

    @Test
    public void validateStatus(){

        /*
        -Get https://fakestoreapi.com/products/
        -Deserialize response
        -Validate status code is 200
        -Get sum of prices
        -Validate it is more than 200$
         */

        RestAssured.baseURI="https://fakestoreapi.com";
        RestAssured.basePath="products";
        Response response=RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).extract().response();

        //deserialization/parsing
        List<Map<String,Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
            } );     //conversion JSON to JAVA

        double totalPrice =0.0;
         for(Map<String,Object> product:parsedResponse){
             //double        (cast)     Object
             double price = Double.parseDouble(product.get("price").toString());
             totalPrice+=price;

         }
        System.out.println("total price : "+totalPrice);
        Assert.assertTrue(totalPrice > 200);


    }
}

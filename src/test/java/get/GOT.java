package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.ContinentPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GOT {
    @Test
    public void getCharactersTest(){

        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters";
        RestAssured.given().header("Accept","application/json") //add header on Postman
                .when().get()
                .then().statusCode(200).log().body();//validate and print this out
    }

    @Test
    public void getSpecificCharacter(){

        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters/10";
        //change JSON to JAVA
        Response response = RestAssured.given().header("Accept","application/json")
                .when().get().then().statusCode(200).log().body().extract()
                .response();
        //log().body() >> just print out the response body.  it is optional when you more professional you don't have to

        //the value in the JSON is different that why we provide Object

        //convert JSON to JAVA by using MAP
        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String,Object>>(){

        });

        System.out.println(deserializedResponse);
        String firstName = (String) deserializedResponse.get("firstName");
        String lastName = String.valueOf(deserializedResponse.get("lastName")); // another way to convert object to string
        Assert.assertEquals("Cateyln",firstName);
        Assert.assertEquals("Stark",lastName);


    }

    @Test
    public void getRequestToEndPoint(){

        /*Task
        make get request to the endpoint to get the continents, deserialize the response and validate the continent count number is 4
         endpoint is in slack class channal
        */

        RestAssured.baseURI="https://thronesapi.com/api/v2/continents";
        Response response = RestAssured.given().accept("application/json")
                .when().get().then().statusCode(200).log().body().extract().response();
        List<Map<String,Object>> parsedResponse= response.as(new TypeRef<List<Map <String,Object>>>() {
        });
        Assert.assertEquals(4,parsedResponse.size());

        //Retrieve all continent names and store them in an List
        List<String> names= new ArrayList<>();
        for(int i=0;i<parsedResponse.size();i++){
            names.add(parsedResponse.get(i).get("name").toString());
        }
        System.out.println(names);

        //second answer
        List<String> continentList = new ArrayList<>();
       for(int i=0; i<parsedResponse.size(); i++){
           Map<String,Object> tempMap = parsedResponse.get(i);

           continentList.add((String) tempMap.get("name"));


       }


    }

    @Test
    public void continentsTest2(){
        //use Pojo class
        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="api/v2/continents";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200).log().body().extract().response();

         //POJO deserialization
        List<ContinentPojo> continentPojoList = new ArrayList<>();

        //continentPojoList=response.as(continentPojoList.getClass());//assigh

        ContinentPojo[] parsedResp = response.as(ContinentPojo[].class);
        Map<String,Integer> continentIdMap =new HashMap<>();

        for(int i =0 ; i<parsedResp.length;i++){

           ContinentPojo continentPojo= parsedResp[i];
           String name = continentPojo.getName();
           int id= continentPojo.getId();
           continentIdMap.put(name,id);

        }
        System.out.println(continentIdMap);

    }
}

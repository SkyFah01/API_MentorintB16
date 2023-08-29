package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ContinentPojo {

    //all the variable must be private
    //To do Poja class you must separate the type of what you get from your response

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name; // name as key on response in Postman

    //then do generate find getter and setter

    @Test
    public void continentTest(){
        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="api/v2/continents/0";//everything after slash(/) is go to Path

        Response response=RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200)
                .log().body()
                .extract().response();

        //parse base on Pojo class ,as parameter
        //the data type will be ContinentPojo
        //no map, depending what you like to sue --> Map or Pojo
        ContinentPojo parseResponse = response.as(ContinentPojo.class);
        parseResponse.getId(); // give the id of value
        int id = parseResponse.getId();
        String name = parseResponse.getName();
        Assert.assertEquals(0,id);
        Assert.assertEquals("Westeros",name);
        //will be iterating over
    }


}

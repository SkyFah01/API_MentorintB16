package get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWalNameOfPlanetPojo;
import pojo.StarWarsCharacterPojo;

import static io.restassured.RestAssured.*;

public class StarWars {

    @Test
    public void getCharacterTest(){

         //opt + enter --> short cut to call " Response response "
        Response response = given().accept("application/json")
                .when().get("https://swapi.dev/api/people/1")
                .then().statusCode(200)
                .extract().response();

        //opt+enter = short cut >> StarWarsCharacterPojo parsedResponse
        //to set all the value to this class
        StarWarsCharacterPojo parsedResponse = response.as(StarWarsCharacterPojo.class);
        String expectedName = "Luke Skywalker";
        String actualName = parsedResponse.getName();

        Assert.assertEquals("Failed to validate SW character name",expectedName,actualName);// write when something failed
        //UnrecognizedPropertyException --> it will let you know where you fail exp --> skin_color

    }

    @Test
    public void nameOfPlanet(){

        /*
        Get https://swapi.dev/api/planets/1
        Validate status code Name,population ,terrain

         */
        RestAssured.baseURI="https://swapi.dev";
        RestAssured.basePath="api/planets/1";
        Response response = given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().response();
        StarWalNameOfPlanetPojo parsedResponse = response.as(StarWalNameOfPlanetPojo.class);

        Assert.assertEquals("Tatooine",parsedResponse.getName());
        Assert.assertEquals("200000",parsedResponse.getPopulation());
        Assert.assertEquals("desert",parsedResponse.getTerrain());




    }

}

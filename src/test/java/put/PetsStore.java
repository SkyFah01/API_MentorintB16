package put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import utils.PayloadUtils;

public class PetsStore {
    @Test
    public void updatePetTest(){
        /*
        1. Create a pet --> create a path (POST)
        2. List a pet --> send a get Request to make sure that path was created
        3.Update a pet --> update the same path
        4.List a pet --> will send another get request to make sure that path was updated

         */

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        int originalPetId = 888998; // we are not comparing to postman id that you provide, on postman just for reference
        String originalPetName = "Alfa";
        //1. create pet --> create a path (POST)
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(originalPetId,originalPetName))
                .when().post()
                .then().statusCode(200)
                .body("id", Matchers.equalTo(originalPetId))
                .body("name", Matchers.equalTo(originalPetName));

        //2. Get pet --> send a get Request to make sure that path was created

         RestAssured.given().accept(ContentType.JSON)
                 .when().get(originalPetId+"")   //parsing to string by add +""
                 .then().statusCode(200)
                 .body("id",Matchers.is(originalPetId))
                 .body("name",Matchers.equalTo(originalPetName));


        //3.PUT >> Update a pet --> update the same path
        String updatePetName = "pluto"; //but use same Pet id


        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(originalPetId,updatePetName))
                .when().put()
                .then().statusCode(200)
                .body("id",Matchers.is(originalPetId))   //validate part
                .body("name",Matchers.is(updatePetName));

        //4.Get pet
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when().get(""+originalPetId)
                .then().statusCode(200)
                .body("id",Matchers.equalTo(originalPetId))
                .body("name",Matchers.equalTo(updatePetName));


    }
}

package delete;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetStorePojo;

import java.util.Map;



public class PetStore {
    @Test
    public void deletePetTest() {
        /**
         * 1.Create a pet
         * 2.Delete a pet and validate is --> 200
         * 3.Delete a pet and validate is  --> 404
         */


        //1.Create a pet --> Sent the POST

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        int petId = 520138;
        String petName = "Boo";
        String petStatus = "playing";

        //create path Java object --> PetStorePojo
        PetStorePojo petPayload = new PetStorePojo();
        petPayload.setId(petId);
        petPayload.setName(petName);
        petPayload.setStatus(petStatus);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPayload)
                .when().post()
                .then().statusCode(200).extract().response();

        //deserialization with POJO
        PetStorePojo parsedResponse = response.as(PetStorePojo.class);
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petStatus, parsedResponse.getStatus());

        //2.Delete a pet
        response = RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId))
                .then().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        /*
        {
    "code": 200,
    "type": "unknown",
    "message": "43526"
        }
         */
        String actualMessage = (String) deserializedResponse.get("message"); //need to casting
        int actualCode = (int) deserializedResponse.get("code");//need to casting
        Assert.assertEquals(200,actualCode);
        Assert.assertEquals(String.valueOf(petId),actualMessage);


      // 3.Delete a pet again and validate is  --> 404 (NOT Found)

        response = RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId))
                .then().statusCode(404).extract().response();
        //check Negative test by put 200 make sure it is fail and change it back to 404



    }
}

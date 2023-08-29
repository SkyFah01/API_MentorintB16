package post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.PayloadUtils;

public class Slack {
    @Test
    public void sendMessageTest() {

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq")
                .body(PayloadUtils.getSlackPayload("Hello from Java code"))
                .when().post()
                .then().statusCode(200)
                .extract().response();

        SlackPojo parsedResponse = response.as(SlackPojo.class);
        String actualMessage = parsedResponse.getMessage().getText();
        Assert.assertTrue(actualMessage.contains("Supattra"));
    }

    @Test
    public void sendMessageTest2() {

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

         Response response =RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq")
                .body(PayloadUtils.getSlackPayload("message sent from Java"))
                .when().post()
                .then().statusCode(200)
                .body("ok", Matchers.is(true))  //get be the ok keys from response
                .body("message.text", Matchers.equalTo("Supattra:message sent from Java"))
                 .extract().response();
                //no limit to add body , if have a lot of fil , Pojo better way

                /*
                {
    "ok": true,  --> validate this first
    "channel": "C05H5S8A50V",
    "ts": "1689986025.230539",
    "message": {
        "bot_id": "B05H5SB9C69",
        "type": "message",
        "text": "Supattra:Hello from PostMan",
        "user": "U05GU8BAB8X",
        "ts": "1689986025.230539",
        "app_id": "A05HYFGRHSL",
        "blocks": [
            {
                 */


    }

}

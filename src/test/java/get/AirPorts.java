package get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class AirPorts {
    @Test
    public void getListOfAirportsTest(){
        //POST --> create
        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="api/airports";
        //if it is a GET call ,Doesn't require a request

        Response response = RestAssured.given().accept(ContentType.JSON) // we only attach the content type header when we need to attach the request
                //but this requires another header, the authorization had it. so i will do header and the value will be authorization
                .header("Authorization", "Bearer xoxb-4764264203175-5572283351303-ChTt1SkoH3zJPsSqvNZ3K8uq")
                //when i sent get request
                .when().get()
                .then().statusCode(200)
                .extract().response();

        //Deserialization  --> JsonPath
        JsonPath parsedResponse = response.jsonPath();
       String nextPageUrl =  parsedResponse.getString("links.next");// parent to child
        System.out.println("Next page url is: " + nextPageUrl);

        /*
         ],
    "links": {
        "first": "https://airportgap.dev-tester.com/api/airports",
        "self": "https://airportgap.dev-tester.com/api/airports",
        "last": "https://airportgap.dev-tester.com/api/airports?page=203",
        "prev": "https://airportgap.dev-tester.com/api/airports",
        "next": "https://airportgap.dev-tester.com/api/airports?page=2"
    }

         */


        //Retrieve id of first airport
      String firstAirportId = parsedResponse.getString("data[0].id");//get me the first data --> data[0]
        System.out.println(firstAirportId);
        Assert.assertEquals("GKA",firstAirportId);
        /*
         {
    "data": [   --> data[0]
        {
            "id": "GKA",
            "type": "airport",
            "attributes": {
                "name": "Goroka Airport",
                "city": "Goroka",
                "country": "Papua New Guinea",
                "iata": "GKA",
                "icao": "AYGA",
                "latitude": "-6.08169",
                "longitude": "145.391998",
                "altitude": 5282,
                "timezone": "Pacific/Port_Moresby"
            }
        },
         */


        //retrieve city of first airport

      String firstAirportCity = parsedResponse.getString("data[0].attributes.city"); // go from parent to child use .
        System.out.println(firstAirportCity);
      Assert.assertEquals("Goroka",firstAirportCity);

          /*
         {
    "data": [
        {
            "id": "GKA",
            "type": "airport",
            "attributes": {
                "name": "Goroka Airport",
                "city": "Goroka",
                "country": "Papua New Guinea",
                "iata": "GKA",
                "icao": "AYGA",
                "latitude": "-6.08169",
                "longitude": "145.391998",
                "altitude": 5282,
                "timezone": "Pacific/Port_Moresby"
            }
        },
         */

    }

}

package advanced;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

public class FootBall {
    @Test
    public void getCompetitionsTest(){

        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";


        Response response= RestAssured.given().accept(ContentType.JSON)
               .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a") // we dont need
               .when().get()
               .then().statusCode(200)
               .body("competitions.find{ it.id == 2006 }.name", Matchers.is("WC Qualification CAF"))
                .body("competitions.findAll { it.area.name == 'Africa' }.name",
                       Matchers.containsInRelativeOrder("WC Qualification CAF","AFC Champions League") )// validate
                .body("competitions.min{ it.id }.name", Matchers.equalTo("FIFA World Cup"))
                .body("competitions.findAll { it.area.countryCode == 'AFR' }.code",
                        Matchers.hasItems("ACL","QCAF"))
                .body("competitions.collect {  it.id }.sum()", //// want to get sum of all id
                        Matchers.greaterThan(10000))// should be more than 10000
                //CAN DO THIS WAY TOO  >> Matchers.greaterThanOrEqualTo(360884))  --> if you know the all result of Sum of id is 360884
                //Negative test , try to make lessThan 10000 --> Matchers.lessThan(10000))

                 // min ->> it mean givem e the minimum of id and return name to me

               .extract().response(); // competitions from postman


          List<String> africaCompetitionList =
                  response.path("competitions.findAll { it.area.name == 'Africa' }.name") ; //  .name --> give me the name of the competition
        System.out.println(africaCompetitionList);//[AFC Champions League, WC Qualification CAF]


        /*
        {
    "count": 173,
    "filters": {},
    "competitions": [
        {
            "id": 2006, --> it.id == 2006  (it representation of ID )
            "area": {
                "id": 2001,
                "name": "Africa", -- > competitions.findAll { it.area.name == 'Africa' }.name"
                "countryCode": "AFR",
                "ensignUrl": null
            },
            "name": "WC Qualification CAF", --> Give me the .name and validate is >> Matchers.is("WC Qualification CAF")
            "code": "QCAF",
            "emblemUrl": null,
            "plan": "TIER_FOUR",
            "currentSeason": {
                "id": 555,
                "startDate": "2019-09-04",
                "endDate": "2021-11-16",
                "currentMatchday": 6,
                "winner": null
            },
         */





    }
}

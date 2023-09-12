package mentoring_1;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utils.API_Calls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.API_Calls.GET;

public class InterviewTask {
  //https://restcountries.com/v3.1/all

    private String expectedCapital ="Bangkok"; // for validate
    private String actualCapital =""; // for validate, prepare the buket for capital from  end point
@Test
    public void validateCapital(){

    RestAssured.baseURI="https://restcountries.com";
    RestAssured.basePath="v3.1/all";

    Response response = RestAssured.given().accept("application/json")
            .when()
            .get()
            .then()
            .statusCode(200)
            .extract().response();
    List<Map<String,Object>> parsed = response.as(new TypeRef<List<Map<String, Object>>>() {
    });
    /*
    {
        "name": {
            "common": "Thailand",
            "official": "Kingdom of Thailand",
            "nativeName": {
                "tha": {
                    "official": "ราชอาณาจักรไทย",
                    "common": "ประเทศไทย"
                }
            }
     */

    List<String> capital = new ArrayList<>(); // for validate capital, bc the capital store in the list from postman
    for (int i = 0; i < parsed.size(); i++) {
        //create temporary map
        Map<String,Object> outerMap = parsed.get(i);
        Map<String,Object> innerMap= (Map<String, Object>) outerMap.get("name"); //cast it

        if(innerMap.get("common").equals("Thailand")){  //get return the value (country)
            //if change country it also print out different capital, It is work dynamic
            //if condition is true I want to print out capital

            //validate part
            capital.add(String.valueOf(outerMap.get("capital")));
            capital = (List<String>) outerMap.get("capital");//cast it
            actualCapital = capital.get(0);//it is ok since we have only 1 data(capital) to do hard code

            break;
        }

    }

    System.out.println(capital);
    System.out.println(actualCapital);
    System.out.println(expectedCapital);
    Assert.assertEquals(expectedCapital,actualCapital);
}

@Test
    public void validateBreakingBadQuotes(){
    //https://api.breakingbadquotes.xyz/v1/quotes


    /*
    [
    {
        "quote": "I want my kids back. I want my life back. Please tell me – How much is enough? How big does this pile have to be?",
        "author": "Skyler White"
    },
    {
        "quote": "Sometimes it just feels better not to talk. At All. About Anything. To Anyone.",
        "author": "Walter White"
    },
     */

    //make change // change
    RestAssured.baseURI="https://api.breakingbadquotes.xyz";
    RestAssured.basePath="v1/quotes";
     Response response = GET("/10"); // or with out .then().log().body().extract().response() , it just printing
    //you can put "/10" as long as you have overload

    List<Map<String,Object>> parsed = response.as(new TypeRef<List<Map<String, Object>>>() {
    });
    for (int i = 0; i <parsed.size() ; i++) {
         Map<String,Object> map = parsed.get(i);

         if(map.get("author").equals("Jesse Pinkman")){
            System.out.println(map.get("quote"));
             System.out.println(map.get("author"));
         }

         /* Make change
         You're my free pass... bitch!
         Jesse Pinkman
         Now, hey, remember, not all learning comes out of books.
         Jesse Pinkman
          */
    }
  // i have overload method , get,post,put
}
}
  /*
    put git but create on the git hub then copy link and go to intelliJ
    click terminal and write --> git remote add origin and repository url
    then --> git status
    then -->git add .   --> mean add everything
    then -->  git commit -m "new api mentoring"
    then --> git push origin


   */

//Make some change here or add new test case

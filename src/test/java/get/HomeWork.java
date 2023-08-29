package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeWork {
    @Test
    public void storeNameAndId() {
        /*
        Homework:
         1.Continents store name and id in a Map (https://thronesapi.com/api/v2/continents)

         */

        RestAssured.baseURI = "https://thronesapi.com/api/v2/continents";
        Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).log().body().extract().response();

        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        Map<String,Object> continentName_Id = new HashMap<>();
        for (int i = 0; i < parsedResponse.size(); i++) {
            continentName_Id.put(String.valueOf((Integer) parsedResponse.get(i).get("id")),(String) parsedResponse.get(i).get("name"));
        }


        System.out.println(continentName_Id);

    }

    @Test
    public void findCarFacts(){
        /*
        2. Find cat facts with less than 50 characters and more than 200 characters
       and store those in a separate List, facts with 50 and less List and facts with 200 and more list
        3. Find non cat related facts and store them in a List (https://catfact.ninja/facts?limit=100)
         */

        RestAssured.baseURI="https://catfact.ninja";
        RestAssured.basePath="facts";
        Response response=RestAssured.given().accept("application/json")
                .queryParam("limit",100)
                .when().get().then().statusCode(200).extract().response();

        Map<String,Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String,Object>>  data = (List<Map<String, Object>>) parsedResponse.get("data");
        Integer expectedFactNumber = 100;
        Integer actualFactNumber = data.size();
        Assert.assertEquals(expectedFactNumber, actualFactNumber);

        List<String> characterLessThan50=new ArrayList<>();
        List<String>  characterMoreThan200 = new ArrayList<>();
        List<String> nonCatFact= new ArrayList<>();
        List<String> result = new ArrayList<>();
        for(int i=0; i<data.size();i++) {
            String characterCatFact = data.get(i).get("fact").toString();

//            if (characterCatFact.length() < 50) {
//                characterLessThan50.add(characterCatFact);
//
//            } else if (characterCatFact.length() > 200) {
//                characterMoreThan200.add(characterCatFact);
//            } else if (!characterCatFact.contains("cat") && !characterCatFact.contains("cats")) {
//                nonCatFact.add(characterCatFact);
//
//            }
            if(characterCatFact.length() <50 || characterCatFact.length() >200 && !characterCatFact.contains("cats")){
                result.add(characterCatFact);
            }

        }

//        System.out.println("cat facts with less than 50 characters" +characterLessThan50);
//        System.out.println("cat facts more than 200 characters"+characterMoreThan200);
//            System.out.println("non cat fact" + nonCatFact);
        System.out.println("cat facts with less than 50 or more than 200 but none cats" +result );


    }
}

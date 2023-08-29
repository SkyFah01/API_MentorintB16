package get;

import io.restassured.RestAssured;
import org.junit.Test;

public class Currency {
   /*
   1. Defined URL/endpoint : http://cf-store.s3-website.us-east-2.amazonaws.com/
   2. Add query string params if needed
   3. Add headers if needed
   4.Define HTTP method (GET)
   5.Send request
    */

    @Test

    public void usdCurrencyTest(){

      //1. Defined URL/endpoint : http://cf-store.s3-website.us-east-2.amazonaws.com/

        RestAssured.baseURI = "https://fakestoreapi.com/products/1";

        //2. Add query string params if needed (No need)
        //3. Add headers if needed (Need)
        RestAssured.given().header("Accept","application/json")
                .when().get()//i want to sent get request
                .then().statusCode(200) //validate what status code I expecting  , if not return 200 the test will be fail
                .log().body(); //log mean printout ,repones body and will show up un console




    }



}

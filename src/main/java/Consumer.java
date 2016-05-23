import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;

public class Consumer {
    int servicePort = 1234;

    public String getPet(String url) {
        return given().accept(ContentType.XML).get(url)
                .andReturn().body().prettyPrint();
    }

    public static void main(String[] args) {
        //curl -X GET --header 'Accept: application/xml' 'http://petstore.swagger.io/v2/pet/1'
        given().accept(ContentType.XML).get("http://petstore.swagger.io/v2/pet/1")
                .andReturn().body().prettyPrint();

    }
}

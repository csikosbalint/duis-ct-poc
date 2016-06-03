import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;

class ConsumerClient {

    public String get(String url) {
        return given().get(url).andReturn().body().print();
    }
}

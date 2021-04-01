package service;

import constant.Params;
import specification.RequestSpec;
import specification.ResponseSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class User extends RequestSpec {

    public User() {
        super(Params.userEndpoint);
    }

    public String getUserId(int statusCode){

        Response response =
        given()
                .spec(super.getRequestSpecification())
                .get()
        .then()
                .spec(ResponseSpec.checkStatusCode(statusCode))
                .extract()
                .response();

        String userId  = response.jsonPath().getString("id");
        System.out.println("Created user ID : " +userId);
        return userId;

    }

}

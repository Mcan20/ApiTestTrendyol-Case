package service;

import constant.Params;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import specification.RequestSpec;
import specification.ResponseSpec;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Artist extends RequestSpec {

    public Artist() {
        super(Params.artistEndpoint);
    }


    public List<String> findTopTracks(String id ,int statusCode){

        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("market","ES")
                        .get("/{id}/top-tracks",id)
                        .then()
                        .spec(ResponseSpec.checkStatusCode(statusCode))
                        .extract()
                        .response();
        List<Object> topTracks =  response.jsonPath().getList("tracks");
        List<String> albumList = new ArrayList<>();
            for(int i= 0;i<3;i++){
               albumList.add(((LinkedHashMap) topTracks.get(i)).get("name").toString());
            }
        return albumList;
    }
}

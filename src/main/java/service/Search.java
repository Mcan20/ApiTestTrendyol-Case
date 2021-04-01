package service;

import constant.Params;
import specification.RequestSpec;
import specification.ResponseSpec;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Search extends RequestSpec {

    public Search() {
        super(Params.searchEndpoint);
    }


    public String findArtist(String trackName ,int statusCode){

        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q",trackName)
                        .queryParam("type","artist")
                        .queryParam("limit","1")
                        .get()
                        .then()
                        .spec(ResponseSpec.checkStatusCode(statusCode))
                        .extract()
                        .response();


        List<String> a  = ((RestAssuredResponseImpl) response).response().path("artists.items.uri");
         return a.get(0);
    }

    public String findTrack(String trackName ,int statusCode){


        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q",trackName)
                        .queryParam("type","track")
                        .queryParam("limit","1")
                        .get()
                        .then()
                        .spec(ResponseSpec.checkStatusCode(statusCode))
                        .extract()
                        .response();


        List<String> a  = ((RestAssuredResponseImpl) response).response().path("tracks.items.uri");
        return a.get(0);
    }

}

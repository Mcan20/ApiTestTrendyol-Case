package service;

import constant.Params;
import specification.RequestSpec;
import specification.ResponseSpec;
import utils.Utils;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreatePlaylist extends RequestSpec {

    public CreatePlaylist() {
        super(Params.createPlaylistEndpoint);
    }

    public String createPlayList(String userId, int statusCode, String jsonFileName){

        JSONObject body = Utils.readJsonFile(jsonFileName);

        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .body(body.toString())
                        .post("/{userId}/playlists",userId)
                .then()
                        .spec(ResponseSpec.checkStatusCode(statusCode))
                        .extract()
                        .response();

        String playlistId =  response.jsonPath().getString("id");
        System.out.println("Oluşturulan playlist ID : "+playlistId);
        return playlistId;
    }

}

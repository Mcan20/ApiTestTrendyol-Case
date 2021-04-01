package service;

import constant.Params;
import specification.RequestSpec;
import specification.ResponseSpec;
import utils.Utils;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Playlists extends RequestSpec {

    public Playlists() {
        super(Params.playlistEndpoint);
    }


    public int getPlaylistSize(String playlistId, int statusCode){
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .get("/{playlist_id}/tracks",playlistId)
                .then()
                        .spec(ResponseSpec.checkStatusCode(statusCode))
                        .extract()
                        .response();
        List<Object> trackList =  response.jsonPath().getList("items");
        System.out.println("Number of track: "+trackList.size());
        return trackList.size();
    }

    public void addTracks(List<String> tracks, String playlistId, int statusCode){

        for(String track : tracks){

            given()
                    .spec(super.getRequestSpecification())
                    .queryParam("uris",track)
                    .post("/{playlist_id}/tracks",playlistId)
                    .then()
                    .spec(ResponseSpec.checkStatusCode(statusCode));
            System.out.println(track+" : is added");
        }

    }

    public void deleteTrack(String trackUri, String playlistId, int statusCode){
        JSONObject deleteBody = Utils.readJsonFile("deleteBody");
        deleteBody.getJSONArray("tracks").getJSONObject(0).put("uri",trackUri);

        given()
                .spec(super.getRequestSpecification())
                .body(deleteBody.toString())
                .delete("/{playlist_id}/tracks",playlistId)
                .then()
                .spec(ResponseSpec.checkStatusCode(statusCode));
        System.out.println(trackUri+" : is deleted");
    }
}

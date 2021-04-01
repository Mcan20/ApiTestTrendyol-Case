import org.junit.Assert;
import org.junit.Test;
import service.User;

import java.util.ArrayList;
import java.util.List;

public class ApiTestCase extends ServiceFactory{

    @Test
    public void Test1(){
        String userId = user.getUserId(200);
        String artistId = search.findArtist("Rammstein",200);
        artist.findTopTracks("6wWVKhxIU2cEi0K81v7HvP",200);
        List<String> albumList = new ArrayList<>();
        albumList =  artist.findTopTracks("6wWVKhxIU2cEi0K81v7HvP",200);
        List<String> trackList = new ArrayList<>();

        for(int i = 0;i<3;i++){
            trackList.add(search.findTrack( albumList.get(i),200));
        }

        String playlistId = createPlaylist.createPlayList(userId,201,"playlistBody");
        playlists.addTracks(trackList,playlistId,201);
        Assert.assertTrue( playlists.getPlaylistSize(playlistId, 200) == 3);
        playlists.deleteTrack(trackList.get(2),playlistId,200);
        Assert.assertTrue(playlists.getPlaylistSize(playlistId, 200) == 2);

    }

}

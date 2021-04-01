import service.*;

public class ServiceFactory {

    public CreatePlaylist createPlaylist;
    public Playlists playlists;
    public Search search;
    public User user;
    public Artist artist;


    public ServiceFactory(){
        this.createPlaylist = new CreatePlaylist();
        this.playlists = new Playlists();
        this.search = new Search();
        this.user = new User();
        this.artist = new Artist();
    }
}

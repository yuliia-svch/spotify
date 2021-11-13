package com.spotify.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "track_playlist",
            joinColumns = @JoinColumn(name = "musicTrack_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<MusicTrack> musicTrackList;

    public Playlist() {
        musicTrackList = new ArrayList<>();
    }

    public Playlist(String name, List<MusicTrack> list) {
        this.name = name;
        this.musicTrackList = list;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MusicTrack> getMusicTrackList() {
        return musicTrackList;
    }

    public void setMusicTrackList(List<MusicTrack> musicTrackList) {
        this.musicTrackList = musicTrackList;
    }

    public boolean addMusicTrack(MusicTrack musicTrack) {
        for(MusicTrack mt : musicTrackList) {
            if(mt.getId() == musicTrack.getId()) {
                return false;
            }
        }
        musicTrackList.add(musicTrack);
        return true;
    }

    public void removeMusicTrack(MusicTrack musicTrack) {
        musicTrackList.remove(musicTrack);
    }
}

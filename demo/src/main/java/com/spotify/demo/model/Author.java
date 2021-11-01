package com.spotify.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private Collection<MusicTrack> musicTracks;

    public Author() { }

    public Author(String name, Collection<MusicTrack> musicTracks) {
        this.name = name;
        this.musicTracks = musicTracks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<MusicTrack> getMusicTracks() {
        return musicTracks;
    }

    public void setMusicTracks(Collection<MusicTrack> musicTracks) {
        this.musicTracks = musicTracks;
    }
}

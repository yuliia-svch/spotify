package com.spotify.demo.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Collection<MusicTrack> musicTracks;

    public Category() { }

    public Category(String name, List<MusicTrack> musicTracks) {
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

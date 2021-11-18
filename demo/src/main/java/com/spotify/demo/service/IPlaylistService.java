package com.spotify.demo.service;

import com.spotify.demo.model.Author;
import com.spotify.demo.model.Category;
import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.model.Playlist;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IPlaylistService {

    Collection<Playlist> getAllPlaylists();

    Collection<Playlist> getPlaylistByName(String name);

    List<MusicTrack> getMusicTracksFromPlaylist(long id);

    Optional<Playlist> getPlaylistById(long id);

    boolean addTrackToPlaylist(MusicTrack musicTrack, long id);

    void removeTrackFromPlaylist(MusicTrack musicTrack, long id);

    void deletePlaylist(long id);

    void savePlaylist(Playlist playlist);
}

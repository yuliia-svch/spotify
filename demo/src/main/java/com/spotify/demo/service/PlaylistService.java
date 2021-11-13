package com.spotify.demo.service;

import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.model.Playlist;
import com.spotify.demo.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public List<Playlist> getPlaylistByName(String name) {
        return playlistRepository.findByName(name);
    }

    @Override
    public List<MusicTrack> getMusicTracksFromPlaylist(long id) {
        return playlistRepository.findById(id).get().getMusicTrackList();
    }

    @Override
    public Optional<Playlist> getPlaylistById(long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public boolean addTrackToPlaylist(MusicTrack musicTrack, long id) {
        Playlist current = playlistRepository.findById(id).get();
        if(current.addMusicTrack(musicTrack)) {
            playlistRepository.save(current);
            return true;
        }
        return false;
    }

    @Override
    public void removeTrackFromPlaylist(MusicTrack musicTrack, long id) {
        Playlist current = playlistRepository.findById(id).get();
        current.removeMusicTrack(musicTrack);
        playlistRepository.save(current);
    }

    @Override
    public void addPlaylist(String name, List<MusicTrack> musicTrackList) {
        playlistRepository.save(new Playlist(name, musicTrackList));
    }

    @Override
    public void deletePlaylist(long id) {
        Optional <Playlist> playlist = playlistRepository.findById(id);
        playlist.ifPresent(playlist1 -> playlistRepository.delete(playlist1));
    }

    @Override
    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }
}

package com.spotify.demo.service;

import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.model.Playlist;
import com.spotify.demo.model.User;
import com.spotify.demo.repository.PlaylistRepository;
import com.spotify.demo.repository.UserRepository;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Playlist> getAllPlaylists() {
        User user = userRepository.findByUsername(getCurrentUsername());
        return user.getPlaylists();
    }

    @Override
    public Collection<Playlist> getPlaylistByName(String name) {
        Collection<Playlist> foundList = playlistRepository.findByName(name);
        User user = userRepository.findByUsername(getCurrentUsername());
        return user.getPlaylists().stream()
                .filter(foundList::contains)
                .collect(Collectors.toList());
     //   return playlistRepository.findByName(name);
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
    public void deletePlaylist(long id) {
        User user = userRepository.findByUsername(getCurrentUsername());
        Optional <Playlist> playlist = playlistRepository.findById(id);
        playlist.ifPresent(user::removePlaylist);
        playlist.ifPresent(playlist1 -> playlistRepository.delete(playlist1));
    }

    @Override
    public void savePlaylist(Playlist playlist) {
        User user = userRepository.findByUsername(getCurrentUsername());
        if(user.getPlaylists().isEmpty()) {
            user.setPlaylists(List.of(playlist));
        } else {
            user.addPlaylist(playlist);
        }
        playlist.setUser(user);
        playlistRepository.save(playlist);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}

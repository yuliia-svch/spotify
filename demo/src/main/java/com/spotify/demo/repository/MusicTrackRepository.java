package com.spotify.demo.repository;

import com.spotify.demo.model.MusicTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicTrackRepository extends JpaRepository<MusicTrack, Long> {
    List<MusicTrack> findByName(String name);
}


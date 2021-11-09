package com.spotify.demo.converter;

import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.model.Author;
import com.spotify.demo.model.Category;
import com.spotify.demo.model.MusicTrack;

public class MusicTrackConverter {

    public static MusicTrackDTO convert(MusicTrack musicTrack) {
        MusicTrackDTO musicTrackDTO = new MusicTrackDTO();
        musicTrackDTO.setId(musicTrack.getId());
        musicTrackDTO.setName(musicTrack.getName());
        musicTrackDTO.setAuthor(musicTrack.getAuthor().getName());
        musicTrackDTO.setYear(musicTrack.getYear());
        musicTrackDTO.setCategory(musicTrack.getCategory().getName());
        musicTrackDTO.setText(musicTrack.getText());
        return musicTrackDTO;
    }

    public static MusicTrack convert(MusicTrackDTO musicTrackDTO) {
        MusicTrack musicTrack = new MusicTrack();
        Author author = new Author();
        Category category = new Category();
        author.setName(musicTrackDTO.getAuthor());
        category.setName(musicTrackDTO.getCategory());
        musicTrack.setId(musicTrackDTO.getId());
        musicTrack.setName(musicTrackDTO.getName());
        musicTrack.setAuthor(author);
        musicTrack.setYear(musicTrackDTO.getYear());
        musicTrack.setCategory(category);
        musicTrack.setText(musicTrackDTO.getText());
        return musicTrack;
    }
}

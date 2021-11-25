package com.spotify.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MusicTrackDTO {

    private long id;

    private String name;

    private String author;

    private int year;

    private String category;

    private String text;
}

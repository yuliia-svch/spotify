package com.spotify.demo.controller;

import com.spotify.demo.service.IMusicTrackService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected IMusicTrackService musicTrackService;
}

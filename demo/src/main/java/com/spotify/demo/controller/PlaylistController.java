package com.spotify.demo.controller;

import com.spotify.demo.converter.MusicTrackConverter;
import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.model.Playlist;
import com.spotify.demo.service.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaylistController extends BaseController{

    private MusicTrack musicTrack;

    @Autowired
    private IPlaylistService playlistService;

    @GetMapping(value = "/list-playlists-to-choose")
    public String showPlaylistChoosePage(ModelMap model) {
        model.put("playlists", playlistService.getAllPlaylists());
        return "list-playlists-to-choose";
    }

    @GetMapping(value = "/all-playlists")
    public String showAllPlaylists(ModelMap model) {
        model.put("playlists", playlistService.getAllPlaylists());
        model.put("activePlaylists", "active");
        return "all-playlists";
    }

    @PostMapping(value = "/refresh-playlists")
    public String refresh( @ModelAttribute("page")String page) {
        return "redirect:/"+page;
    }

    @GetMapping(value = "/add-playlist")
    public String showAddPlaylistPage(@ModelAttribute("page")String page, ModelMap model) {
        model.addAttribute("playlist", new Playlist());
        model.put("page", page);
        return "newPlaylist";
    }

    @GetMapping(value = "/delete-playlist")
    public String deletePlaylist(@RequestParam long id) {
        playlistService.deletePlaylist(id);
        return "redirect:/all-playlists";
    }

    @PostMapping(value = "/add-playlist")
    public String addPlaylist(@Valid Playlist playlist, @ModelAttribute("page")String page,
                              BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.put("playlist", playlist);
            return "newPlaylist";
        }
        playlistService.savePlaylist(playlist);
        return "redirect:/"+page;
    }

    @GetMapping(value = "/add-to-playlist")
    public String addToPlaylist(@RequestParam long id) {
        musicTrack = musicTrackService.getMusicTrackById(id).get();
        return "redirect:/list-playlists-to-choose";
    }

    @PostMapping(value = "/delete-from-playlist")
    public String deleteFromPlaylist(@ModelAttribute("musicTrackId")long musicTrackId,
                                     @ModelAttribute("playlistId")long playlistId) {
        musicTrack = musicTrackService.getMusicTrackById(musicTrackId).get();
        playlistService.removeTrackFromPlaylist(musicTrack, playlistId);
        return "redirect:/see-playlist?id="+playlistId;
    }

    @GetMapping(value = "/choose-playlist")
    public String choosePlaylist(@RequestParam long id) {
       if(playlistService.addTrackToPlaylist(musicTrack, id)) {
           return "redirect:/see-playlist?id="+id + "&message=Track added!";
       }
       return "redirect:/see-playlist?id="+id+"&message=There already is such track in this playlist!";
    }

    @GetMapping(value = "/see-playlist")
    public String seeMore(@RequestParam long id,
                          @RequestParam(value = "message",required = false) String message,
                          ModelMap model) {
        Playlist playlist = playlistService.getPlaylistById(id).get();
        List<MusicTrack> list  = playlist.getMusicTrackList();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : list) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        model.put("id", id);
        model.put("message", message);
        model.put("name", playlistService.getPlaylistById(id).get().getName());
        model.put("musicTracks", dtos);
        return "see-playlist";
    }

    @PostMapping(value = "/search-playlists")
    public String search(@ModelAttribute("search")String search,
                         @ModelAttribute("page")String page,
                         ModelMap model) {
        if (search.equals(""))
            return page;
        model.put("playlists", playlistService.getPlaylistByName(search));
        return page;
    }
}

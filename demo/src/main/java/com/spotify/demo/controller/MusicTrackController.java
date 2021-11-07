package com.spotify.demo.controller;

import com.spotify.demo.converter.MusicTrackConverter;
import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.service.IMusicTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class MusicTrackController {

    @Autowired
    private IMusicTrackService musicTrackService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping(value = "/userPage")
    public String userPage(ModelMap model) {
        model.put("musicTracks", getTracks());
        return "userPage";
    }

    @GetMapping(value = "/list-musicTracks")
    public String showMusicTracks(ModelMap model) {
        model.put("musicTracks", getTracks());
        return "list-musicTracks";
    }

    @GetMapping(value = "/list-allMusicTracks")
    public String getAllMusicTracks(ModelMap model) {
        List<MusicTrack> musicTrackList = musicTrackService.getAllMusicTracks();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : musicTrackList) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        model.put("musicTracks", dtos);
        return "list-musicTracks";
    }

    @PostMapping(value = "/refresh")
    public String refresh(ModelMap model, @ModelAttribute("page")String page) {
        List<MusicTrack> musicTrackList = musicTrackService.getAllMusicTracks();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : musicTrackList) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        model.put("musicTracks", dtos);
        return page;
    }

    @GetMapping(value = "/add-musicTrack")
    public String showAddMusicTrackPage(ModelMap model) {
        model.addAttribute("musicTrack", new MusicTrack());
        return "musicTrack";
    }

    @GetMapping(value = "/delete-musicTrack")
    public String deleteMusicTrack(@RequestParam long id) {
        musicTrackService.deleteMusicTrack(id);
        return "redirect:/list-allMusicTracks";
    }

    @GetMapping(value = "/update-musicTrack")
    public String showUpdateMusicTrackPage(@RequestParam long id, ModelMap model) {
        MusicTrackDTO musicTrack = MusicTrackConverter.convert(musicTrackService.getMusicTrackById(id).get());
        model.put("musicTrack", musicTrack);
        return "musicTrack";
    }

    @PostMapping(value = "/add-musicTrack")
    public String addMusicTrack(@Valid MusicTrackDTO musicTrackDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "musicTrack";
        }

        musicTrackService.saveMusicTrack(MusicTrackConverter.convert(musicTrackDTO));
        return "redirect:/list-allMusicTracks";
    }

    @PostMapping(value = "/update-musicTrack")
    public String updateMusicTrack(@Valid MusicTrackDTO musicTrackDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "musicTrack";
        }

        musicTrackService.updateMusicTrack(MusicTrackConverter.convert(musicTrackDTO));
        return "redirect:/list-allMusicTracks";
    }

    @PostMapping(value = "/sort")
    public String sort(@ModelAttribute("option")String option, @ModelAttribute("page")String page) {
        if (option.equalsIgnoreCase("Sort by: "))
            return "redirect:/" + page;
        musicTrackService.sortMusicTracks(option);
        return "redirect:/" + page;
    }

    @PostMapping(value = "/search")
    public String search(@ModelAttribute("search")String search, @ModelAttribute("page")String page) {
        if (search.equals(""))
            return "redirect:/" + page;
        musicTrackService.getMusicTrackByCriteria(search);
        return "redirect:/" + page;
    }

    private List<MusicTrackDTO> getTracks() {
        Collection<MusicTrack> list = musicTrackService.getRecentChanges();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : list) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        return dtos;
    }
}

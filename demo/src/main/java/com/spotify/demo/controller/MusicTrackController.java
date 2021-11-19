package com.spotify.demo.controller;

import com.spotify.demo.converter.MusicTrackConverter;
import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.model.MusicTrack;
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
public class MusicTrackController extends BaseController{

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping(value = "/userPage")
    public String userPage(ModelMap model) {
        model.put("musicTracks", getRecent());
        return "userPage";
    }

    @GetMapping(value = "/userPageAll")
    public String userPageAll(@RequestParam(value = "username", required = false) String username,
                              ModelMap model) {
        model.put("username", username);
        model.put("musicTracks", getAll());
        return "userPage";
    }

    @GetMapping(value = "/list-musicTracks")
    public String showMusicTracks(ModelMap model) {
        model.put("musicTracks", getRecent());
        return "list-musicTracks";
    }

    @GetMapping(value = "/list-allMusicTracks")
    public String getAllMusicTracks(ModelMap model) {
        model.put("musicTracks", getAll());
        return "list-musicTracks";
    }

    @PostMapping(value = "/refresh")
    public String refresh(ModelMap model, @ModelAttribute("page")String page) {
        model.put("musicTracks", getAll());
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
    public String addMusicTrack(@Valid MusicTrackDTO musicTrackDTO, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.put("musicTrack", musicTrackDTO);
            return "musicTrack";
        }

        musicTrackService.saveMusicTrack(MusicTrackConverter.convert(musicTrackDTO));
        return "redirect:/list-allMusicTracks";
    }

    @PostMapping(value = "/update-musicTrack")
    public String updateMusicTrack(@Valid MusicTrackDTO musicTrackDTO, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.put("musicTrack", musicTrackDTO);
            return "musicTrack";
        }

        musicTrackService.updateMusicTrack(MusicTrackConverter.convert(musicTrackDTO));
        return "redirect:/list-allMusicTracks";
    }

    @GetMapping(value = "/seeMore")
    public String seeMore(@RequestParam long id, @RequestParam String page, ModelMap model) {
        MusicTrackDTO musicTrack = MusicTrackConverter.convert(musicTrackService.getMusicTrackById(id).get());
        model.put("name", musicTrack.getName());
        model.put("author", musicTrack.getAuthor());
        model.put("year", musicTrack.getYear());
        model.put("category", musicTrack.getCategory());
        model.put("text", musicTrack.getText());
        model.put("page", page);
        return "seeMore";
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

    @PostMapping(value = "/search-by-substring")
    public String searchBySubstring(@ModelAttribute("search")String search,
                                    @ModelAttribute("page")String page,
                                    ModelMap model) {
        if (search.equals(""))
            return "redirect:/" + page;
        musicTrackService.searchBySubstring(search);
        return "redirect:/" + page;
    }

    private List<MusicTrackDTO> getRecent() {
        Collection<MusicTrack> list = musicTrackService.getRecentChanges();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : list) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        return dtos;
    }

    public List<MusicTrackDTO> getAll() {
        Collection<MusicTrack> list = musicTrackService.getAllMusicTracks();
        List<MusicTrackDTO> dtos = new ArrayList<>();
        for(MusicTrack mt : list) {
            dtos.add(MusicTrackConverter.convert(mt));
        }
        return dtos;
    }
}

package com.spotify.demo.service;

import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.helpers.MusicTrackSort;
import com.spotify.demo.model.Author;
import com.spotify.demo.model.Category;
import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.repository.AuthorRepository;
import com.spotify.demo.repository.CategoryRepository;
import com.spotify.demo.repository.MusicTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MusicTrackService implements IMusicTrackService {

    private Collection<MusicTrack> musicTrackList = new ArrayList<>();

    @Autowired
    private MusicTrackRepository musicTrackRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<MusicTrack> getAllMusicTracks() {
        return musicTrackRepository.findAll();
    }

    public Collection<MusicTrack> getRecentChanges() {
        return musicTrackList;
    }

    public void getMusicTrackByName(String name) {
        musicTrackList.clear();
        musicTrackList = musicTrackRepository.findByName(name);
    }

    public void getMusicTrackByAuthor(String name) {
        musicTrackList.clear();
        Author auth = authorRepository.findByName(name);
        if(auth != null)
            musicTrackList = auth.getMusicTracks();
    }

    public void getMusicTrackByCategory(String name) {
        musicTrackList.clear();
        Category cat = categoryRepository.findByName(name);
        if(cat != null)
            musicTrackList = cat.getMusicTracks();
    }

    public void getMusicTrackByCriteria(String name) {
        getMusicTrackByName(name);
        if(musicTrackList.isEmpty())
            getMusicTrackByAuthor(name);

        if(musicTrackList.isEmpty())
            getMusicTrackByCategory(name);
    }

    public Optional<MusicTrack> getMusicTrackById(long id) {
        return musicTrackRepository.findById(id);
    }

    public void updateMusicTrack(MusicTrack musicTrack) {
        Author author = authorRepository.findByName(musicTrack.getAuthor().getName());
        Category category = categoryRepository.findByName(musicTrack.getCategory().getName());
        if(author==null){
            author = musicTrack.getAuthor();
            author.setMusicTracks(List.of(musicTrack));
        } else {
            Collection<MusicTrack> list = author.getMusicTracks();
            list.add(musicTrack);
            author.setMusicTracks(list);
        }
        if(category==null){
            category = musicTrack.getCategory();
            category.setMusicTracks(List.of(musicTrack));

        } else {
            Collection<MusicTrack> list = category.getMusicTracks();
            list.add(musicTrack);
            category.setMusicTracks(list);
        }
        categoryRepository.save(category);
        authorRepository.save(author);
        musicTrackRepository.save(musicTrack);
    }

    public void addMusicTrack(String name, Author author, int year, Category category) {
        musicTrackRepository.save(new MusicTrack(name, author, year, category));
    }

    public void deleteMusicTrack(long id) {
        Optional <MusicTrack> musicTrack1 = musicTrackRepository.findById(id);
        musicTrack1.ifPresent(musicTrack -> musicTrackRepository.delete(musicTrack));
    }

    public void saveMusicTrack(MusicTrack musicTrack) {
        Author author = authorRepository.findByName(musicTrack.getAuthor().getName());
        Category category = categoryRepository.findByName(musicTrack.getCategory().getName());
        if(author==null){
            author = musicTrack.getAuthor();
            author.setMusicTracks(List.of(musicTrack));
        } else {
            Collection<MusicTrack> list = author.getMusicTracks();
            list.add(musicTrack);
            author.setMusicTracks(list);
        }
        if(category==null){
            category = musicTrack.getCategory();
            category.setMusicTracks(List.of(musicTrack));

        } else {
            Collection<MusicTrack> list = category.getMusicTracks();
            list.add(musicTrack);
            category.setMusicTracks(list);
        }
        musicTrack.setAuthor(author);
        musicTrack.setCategory(category);
        categoryRepository.save(category);
        authorRepository.save(author);
        musicTrackRepository.save(musicTrack);
    }

    public void sortMusicTracks(String value) {
        List<MusicTrack> musicTracks = new ArrayList<>();
        switch(value) {
            case "name":
                musicTracks = MusicTrackSort.sortByName(musicTrackRepository.findAll());
                break;
            case "author":
                musicTracks = MusicTrackSort.sortByAuthor(musicTrackRepository.findAll());
                break;
            case "year":
                musicTracks = MusicTrackSort.sortByYear(musicTrackRepository.findAll());
                break;
            case "category":
                musicTracks = MusicTrackSort.sortByCategory(musicTrackRepository.findAll());
        }
        musicTrackList = musicTracks;
    }
}

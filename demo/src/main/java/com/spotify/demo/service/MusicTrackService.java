package com.spotify.demo.service;

import com.spotify.demo.model.Author;
import com.spotify.demo.model.Category;
import com.spotify.demo.model.MusicTrack;
import com.spotify.demo.repository.AuthorRepository;
import com.spotify.demo.repository.CategoryRepository;
import com.spotify.demo.repository.MusicTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MusicTrackService implements IMusicTrackService {

    @Autowired
    private MusicTrackRepository musicTrackRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<MusicTrack> getAllMusicTracks() {
        return musicTrackRepository.findAll();
    }

    public List<MusicTrack> getMusicTrackByName(String name) {
        return musicTrackRepository.findByName(name);
    }

    public Optional<MusicTrack> getMusicTrackById(long id) {
        return musicTrackRepository.findById(id);
    }

    public void updateMusicTrack(MusicTrack musicTrack) {
        Optional<Author> author = authorRepository.findById(musicTrack.getAuthor().getId());
        Optional<Category> category = categoryRepository.findById(musicTrack.getCategory().getId());
        if(author.isEmpty()){
            Author auth = musicTrack.getAuthor();
            auth.setMusicTracks(Arrays.asList(musicTrack));
            authorRepository.save(auth);
        } else {
            Author auth = author.get();
            Collection<MusicTrack> list = auth.getMusicTracks();
            list.add(musicTrack);
            auth.setMusicTracks(list);
            authorRepository.save(auth);
        }

        if(category.isEmpty()){
            Category cat = musicTrack.getCategory();
            cat.setMusicTracks(Arrays.asList(musicTrack));
            categoryRepository.save(cat);
        } else {
            Category cat = category.get();
            Collection<MusicTrack> list = cat.getMusicTracks();
            list.add(musicTrack);
            cat.setMusicTracks(list);
            categoryRepository.save(cat);
        }
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
        Optional<Author> author = authorRepository.findById(musicTrack.getAuthor().getId());
        Optional<Category> category = categoryRepository.findById(musicTrack.getCategory().getId());
        if(author.isEmpty()){
            Author auth = musicTrack.getAuthor();
            auth.setMusicTracks(Arrays.asList(musicTrack));
            authorRepository.save(auth);
        } else {
            Author auth = author.get();
            Collection<MusicTrack> list = auth.getMusicTracks();
            list.add(musicTrack);
            auth.setMusicTracks(list);
            authorRepository.save(auth);
        }

        if(category.isEmpty()){
            Category cat = musicTrack.getCategory();
            cat.setMusicTracks(Arrays.asList(musicTrack));
            categoryRepository.save(cat);
        } else {
            Category cat = category.get();
            Collection<MusicTrack> list = cat.getMusicTracks();
            list.add(musicTrack);
            cat.setMusicTracks(list);
            categoryRepository.save(cat);
        }

        musicTrackRepository.save(musicTrack);
    }
}

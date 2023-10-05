package com.ubeydekara.elasticsearch.controller;

import com.ubeydekara.elasticsearch.domain.Song;
import com.ubeydekara.elasticsearch.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public Iterable<Song> findAll() {
        return songService.findAll();
    }

    @GetMapping("/suggest/{name}")
    public Set<String> suggestByName(@PathVariable String name) {
        return songService.suggestByName(name);
    }

    @PostMapping
    public Song save(@RequestBody Song song) {
        return songService.save(song);
    }
}

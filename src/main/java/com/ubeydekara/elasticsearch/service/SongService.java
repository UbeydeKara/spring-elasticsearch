package com.ubeydekara.elasticsearch.service;

import com.ubeydekara.elasticsearch.domain.Song;
import com.ubeydekara.elasticsearch.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public Set<String> suggestByName(String name) {
        return songRepository.suggestSongName(name).stream().map(Song::getName).collect(Collectors.toSet());
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }
}

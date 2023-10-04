package com.ubeydekara.elasticsearch.repository;

import com.ubeydekara.elasticsearch.domain.Song;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends ElasticsearchRepository<Song, String> {

    @Query("{\"bool\": {\"must\": {\"match_phrase_prefix\": {\"name\": \"?0\"}}}}")
    List<Song> suggestSongName(String name);

}

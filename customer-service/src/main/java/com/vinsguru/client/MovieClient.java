package com.vinsguru.client;

import com.vinsguru.domain.Genre;
import com.vinsguru.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


// This class is used for inter-service communication
@Service
public class MovieClient {

    private static final Logger log = LoggerFactory.getLogger(MovieClient.class);

    // RestClient bean is created in ServiceClientConfig configuration file.
    private final RestClient client;

    public MovieClient(RestClient client) {
        this.client = client;
    }

    public List<MovieDto> getMovies(Genre genre) {
        log.info("genre: {}", genre);
        var list = this.client.get()
                .uri("/api/movies/{genre}", genre)
                .retrieve()
                .body(new ParameterizedTypeReference<List<MovieDto>>() {
                });
        log. info("recieved movies: {}", list);
        return list;
    }


}

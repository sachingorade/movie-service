package com.ts.movies.service.impl;

import com.ts.movies.model.Movie;
import com.ts.movies.model.PageData;
import com.ts.movies.persistence.MovieRepository;
import com.ts.movies.service.MovieService;
import com.ts.movies.web.api.ApiSortDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        // We set id to null so that a new movie is created with rest of the data
        movie.setId(null);
        return movieRepository.save(movie);
    }

    @Override
    public PageData<Movie> getMoviesBy(int page, int size, String sortBy, ApiSortDir sortDir) {
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sortDir == ApiSortDir.ASC ? sort.ascending() : sort.descending());
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return new PageData<>(moviePage.stream().collect(Collectors.toList()), moviePage.getTotalElements());
    }

}

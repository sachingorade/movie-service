package com.ts.movies.service;

import com.ts.movies.model.Movie;
import com.ts.movies.model.PageData;
import com.ts.movies.web.api.ApiSortDir;

import java.util.List;

public interface MovieService {

    Movie createMovie(Movie movie);

    PageData<Movie> getMoviesBy(int page, int size, String sortBy, ApiSortDir sortDir);
}

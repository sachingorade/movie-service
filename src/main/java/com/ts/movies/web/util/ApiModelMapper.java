package com.ts.movies.web.util;

import com.ts.movies.model.Movie;
import com.ts.movies.web.api.ApiMovie;
import org.springframework.stereotype.Component;

@Component
public class ApiModelMapper {

    public Movie toMovie(ApiMovie apiMovie) {
        return new Movie(apiMovie.getId(), apiMovie.getName(), apiMovie.getLengthInMinutes(),
                apiMovie.getYear(), null);
    }

    public ApiMovie toApiMovie(Movie movie) {
        return new ApiMovie(movie.getId(), movie.getName(), movie.getLength(), movie.getYear());
    }
}

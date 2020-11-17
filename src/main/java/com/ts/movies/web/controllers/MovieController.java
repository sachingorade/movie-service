package com.ts.movies.web.controllers;

import com.ts.movies.model.Movie;
import com.ts.movies.model.PageData;
import com.ts.movies.service.MovieService;
import com.ts.movies.web.api.ApiMovie;
import com.ts.movies.web.api.ApiPageResponse;
import com.ts.movies.web.api.ApiSortDir;
import com.ts.movies.web.util.ApiEndpoint;
import com.ts.movies.web.util.ApiModelMapper;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.ts.movies.web.util.ApiEndpoint.PARAM_PAGE;
import static com.ts.movies.web.util.ApiEndpoint.PARAM_SIZE;
import static com.ts.movies.web.util.ApiEndpoint.PARAM_SORT_BY;
import static com.ts.movies.web.util.ApiEndpoint.PARAM_SORT_DIR;

@RestController
public class MovieController {

    private final MovieService movieService;
    private final ApiModelMapper apiModelMapper;

    public MovieController(MovieService movieService, ApiModelMapper apiModelMapper) {
        this.movieService = movieService;
        this.apiModelMapper = apiModelMapper;
    }

    @PostMapping(ApiEndpoint.MOVIES)
    public ApiMovie createMovie(@RequestBody ApiMovie apiMovie) {
        return apiModelMapper.toApiMovie(movieService.createMovie(apiModelMapper.toMovie(apiMovie)));
    }

    @GetMapping(ApiEndpoint.MOVIES)
    public ApiPageResponse<ApiMovie> getMovies(@RequestParam(value = PARAM_PAGE, defaultValue = "0", required = false) int page,
                                           @RequestParam(value = PARAM_SIZE, defaultValue = "20", required = false) int size,
                                           @RequestParam(value = PARAM_SORT_BY, defaultValue = "id", required = false) String sortBy,
                                       @RequestParam(value = PARAM_SORT_DIR, defaultValue = "ASC", required = false) ApiSortDir sortDir) {
        PageData<Movie> moviePageData = movieService.getMoviesBy(page, size, sortBy, sortDir);
        return new ApiPageResponse<>(
                moviePageData.getData().parallelStream().map(apiModelMapper::toApiMovie).collect(Collectors.toList()),
                moviePageData.getTotalCount()
        );
    }
}

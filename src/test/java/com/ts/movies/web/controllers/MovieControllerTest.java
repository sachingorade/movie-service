package com.ts.movies.web.controllers;

import com.ts.movies.model.Movie;
import com.ts.movies.service.MovieService;
import com.ts.movies.web.api.ApiMovie;
import com.ts.movies.web.util.ApiModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Mock
    private ApiModelMapper apiModelMapper;

    private void mockApiModelMapperForRealCalls() {
        when(apiModelMapper.toApiMovie(any(Movie.class))).thenCallRealMethod();
        when(apiModelMapper.toMovie(any(ApiMovie.class))).thenCallRealMethod();
    }

    @Test
    void testCreateMovie() {
        mockApiModelMapperForRealCalls();
        when(movieService.createMovie(any(Movie.class))).then((Answer<Movie>) invocationOnMock -> invocationOnMock.getArgument(0));
        ApiMovie apiMovie = new ApiMovie(1L, "Test Movie", 120, 2020);

        movieController.createMovie(apiMovie);

        verify(movieService).createMovie(any(Movie.class));
    }

}
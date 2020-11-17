package com.ts.movies.service.impl;

import com.ts.movies.model.Movie;
import com.ts.movies.model.PageData;
import com.ts.movies.persistence.MovieRepository;
import com.ts.movies.web.api.ApiSortDir;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;
    @Mock
    private MovieRepository movieRepository;

    @Test
    void testCreateMovie() {
        Movie movie = new Movie(null, "My Movie", 120, 2020, null);

        movieService.createMovie(movie);

        verify(movieRepository).save(Mockito.same(movie));
    }

    @Test
    void testCreateMovieWithId_ShouldCreateNewMovie() {
        Movie movie = new Movie(1L, "My Movie", 120, 2020, null);

        movieService.createMovie(movie);

        ArgumentCaptor<Movie> movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(movieArgumentCaptor.capture());
        assertNull(movieArgumentCaptor.getValue().getId());
    }

    @Test
    void testGetMoviesByPage_VerifySortParamsAndData() {
        Movie movie = new Movie(1L, "My Movie", 120, 2020, null);
        List<Movie> movies = Collections.singletonList(movie);
        Page<Movie> page = mock(Page.class);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.stream()).thenReturn(movies.stream());
        when(movieRepository.findAll(any(Pageable.class))).thenReturn(page);

        PageData<Movie> moviePageData = movieService.getMoviesBy(1, 20, "id", ApiSortDir.ASC);

        ArgumentCaptor<Pageable> pageableArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(movieRepository).findAll(pageableArgumentCaptor.capture());
        assertEquals(1, pageableArgumentCaptor.getValue().getPageNumber());
        assertEquals(20, pageableArgumentCaptor.getValue().getPageSize());
        assertEquals(Sort.by("id").ascending(), pageableArgumentCaptor.getValue().getSort());

        assertEquals(1, moviePageData.getTotalCount());
        assertEquals(1, moviePageData.getData().size());
        assertEquals(movie, moviePageData.getData().get(0));
    }

}
package com.ts.movies.web.controllers;

import com.ts.movies.TestUtil;
import com.ts.movies.web.util.ApiEndpoint;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class MovieControllerIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtil testUtil;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void testCreateMovie() throws Exception {
        mockMvc.perform(post(ApiEndpoint.MOVIES)
        .contentType(MediaType.APPLICATION_JSON)
        .content(testUtil.getResourceContents("mocks/movies/create-movie-request.json")))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    JSONAssert.assertEquals(testUtil.getResourceContents("mocks/movies/create-movie-response.json"),
                            result.getResponse().getContentAsString(), true);
                });
    }

    @Test
    void testGetMovies_NoData() throws Exception {
        mockMvc.perform(get(ApiEndpoint.MOVIES))
                .andExpect(status().isOk())
                .andExpect(result -> {
                   JSONAssert.assertEquals(testUtil.getResourceContents("mocks/movies/get-movies-empty.json"),
                           result.getResponse().getContentAsString(), true);
                });
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void testGetMovies_WithData() throws Exception {
        testCreateMovie();

        mockMvc.perform(get(ApiEndpoint.MOVIES))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    JSONAssert.assertEquals(testUtil.getResourceContents("mocks/movies/get-movies-data.json"),
                            result.getResponse().getContentAsString(), true);
                });
    }

}
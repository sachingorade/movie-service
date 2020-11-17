package com.ts.movies.web.api;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Validated
public class ApiMovie {

    private Long id;
    @NotNull @Size(min = 1, max = 255)
    private String name;
    @Positive
    private int lengthInMinutes;
    @Positive
    private int year;

    public ApiMovie() {
    }

    public ApiMovie(Long id, @NotNull @Size(min = 1, max = 255) String name, @Positive int lengthInMinutes,
                    int year) {
        this.id = id;
        this.name = name;
        this.lengthInMinutes = lengthInMinutes;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiMovie apiMovie = (ApiMovie) o;
        return Objects.equals(id, apiMovie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ApiMovie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                ", year=" + year +
                '}';
    }
}

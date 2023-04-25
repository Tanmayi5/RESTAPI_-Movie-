// package com.learn.movies.Entity;
package com.example.Movies.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String movieName;
    private Long movieId;
    private String movieLanguage;
    private String movieGenre;
}
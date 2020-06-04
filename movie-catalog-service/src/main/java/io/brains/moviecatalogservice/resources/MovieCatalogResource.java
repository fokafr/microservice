package io.brains.moviecatalogservice.resources;

import io.brains.moviecatalogservice.models.CatalogItem;
import io.brains.moviecatalogservice.models.Movie;
import io.brains.moviecatalogservice.models.Rating;
import io.brains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

 @Autowired
 private RestTemplate restTemplate;

 @Autowired
 private DiscoveryClient discoveryClient;

 @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
     //RestTemplate restTemplate = new RestTemplate();
     //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/foo", Movie.class);

     /* List<Rating> ratings = Arrays.asList(
              new Rating("1234",4),
              new Rating("5678",3)
      );*/
//     UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);
     UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);

     //get all rated movies Id
    return ratings.getUserRating().stream()
            .map(rating -> {
                //For each movie Id, call call info service and get detail
                Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
               //Put Them all Together
                return new CatalogItem(movie.getName(),"Desc",rating.getRating());
            })
            .collect(Collectors.toList());
     //for each movieId call movie info service and get details


     //put them all together

 }

}

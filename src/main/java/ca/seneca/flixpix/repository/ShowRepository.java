package ca.seneca.flixpix.repository;

import ca.seneca.flixpix.models.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShowRepository extends MongoRepository<Show, String> {
    List<Show> findByFeaturedTrue();
    List<Show> findByTypeAndFeaturedTrue(String type);
    List<Show> findShowsByType(String type);
    List<Show> findByTitleContainingIgnoreCase(String title);
    List<Show> findShowsByTypeAndTitleContainingIgnoreCase(String type, String title);
}


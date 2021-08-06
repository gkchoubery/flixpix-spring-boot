package ca.seneca.flixpix.services;

import ca.seneca.flixpix.models.Show;
import ca.seneca.flixpix.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowsService {

    @Autowired
    private ShowRepository showRepository;

    public Optional<Show> getShowById(String id) {
        return showRepository.findById(id);
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }

    public Boolean checkById(String id) {
        return showRepository.existsById(id);
    }

    public void deleteShowById(String id) {
        showRepository.deleteById(id);
    }

    public List<Show> getTVSeries() {
        return showRepository.findShowsByType("series");
    }

    public List<Show> getMovies() {
        return showRepository.findShowsByType("movies");
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show updateShow(Show show) {
        return showRepository.save(show);
    }

    public List<Show> getFeaturedShows(@Nullable String type) {
        if (type == null) return showRepository.findByFeaturedTrue();
        return showRepository.findByTypeAndFeaturedTrue(type);
    }

    public List<Show> searchByQueryAndType(String q, @Nullable String type) {
        if (type == null) return showRepository.findByTitleContainingIgnoreCase(q);
        return showRepository.findShowsByTypeAndTitleContainingIgnoreCase(type, q);
    }
}

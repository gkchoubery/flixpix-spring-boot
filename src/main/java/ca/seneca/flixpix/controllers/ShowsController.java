package ca.seneca.flixpix.controllers;

import ca.seneca.flixpix.BaseController;
import ca.seneca.flixpix.models.Show;
import ca.seneca.flixpix.services.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shows")
public class ShowsController extends BaseController {

    @Autowired
    private ShowsService showsService;

    @GetMapping("/")
    public ResponseEntity<List<Show>> getShows() {
        return success(showsService.getAllShows());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Show> createShow(@Valid @RequestBody Show show) {
        return success(showsService.createShow(show));
    }

    @GetMapping("/series")
    public ResponseEntity<List<Show>> getTvShows() {
        return success(showsService.getTVSeries());
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Show>> getMovies() {
        return success(showsService.getMovies());
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Show>> getFeaturedShows(
            @RequestParam(value = "type", required = false) String type) {
        if (type == null || type.equals("movies") || type.equals("series")) {
            return success(showsService.getFeaturedShows(type));
        } else return badRequest();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Show>> searchShows(
            @RequestParam("q") String q,
            @RequestParam(value = "type", required = false) String type) {
        if (type == null || type.equals("movies") || type.equals("series")) {
            return success(showsService.searchByQueryAndType(q, type));
        } else return badRequest();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable("id") String id) {
        Optional<Show> show = showsService.getShowById(id);
        if (show.isPresent()) {
            return success(show.get());
        } else {
            return notFound();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShowById(
            @PathVariable("id") String id,
            @Valid @RequestBody Show show) {

        Optional<Show> showData = showsService.getShowById(id);
        if (showData.isPresent()) {
            Show _show = showData.get();
            _show.setTitle(show.getTitle());
            _show.setFeatured(show.getFeatured());
            _show.setBanner(show.getBanner());
            _show.setPoster(show.getPoster());
            _show.setDescription(show.getDescription());
            _show.setBuyPrice(show.getBuyPrice());
            _show.setRentPrice(show.getRentPrice());
            return success(showsService.updateShow(_show));
        }
        return notFound();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShowById(@PathVariable("id") String id) {
        if (showsService.checkById(id)) {
            showsService.deleteShowById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return notFound();
    }
}

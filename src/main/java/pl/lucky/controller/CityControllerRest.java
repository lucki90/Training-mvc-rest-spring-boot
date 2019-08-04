package pl.lucky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lucky.model.City;
import pl.lucky.repository.CityRepository;

import java.util.Comparator;
import java.util.List;

@RestController("/api/cities")
public class CityControllerRest {

    private CityRepository cityRepo;

    @Autowired
    public CityControllerRest(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities(@RequestParam(defaultValue = "name") String orderBy) {

        List<City> cities = cityRepo.findAll();

        if (orderBy.equals("name")) {
            cities.sort(Comparator.comparing(City::getName));
        } else if (orderBy.equals("population")) {
            cities.sort(Comparator.comparing(City::getPopulation));
        }
        return cities;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> getCity(@PathVariable Long id) {

        return cityRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCity(@RequestBody City city) {
        cityRepo.save(city);
    }
}

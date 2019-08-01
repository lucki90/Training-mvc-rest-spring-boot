package pl.lucky.controller;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lucky.model.City;

import java.util.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private List<City> cities;

    public CityController() {
        cities = Collections.synchronizedList(new ArrayList<City>());
    }

    @GetMapping("/{id}")
    public City getCity(@PathVariable(name = "id") int id) {
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities(@RequestParam/*(required = false)*/(defaultValue = "name") String orderBy) {

//        if (orderBy==null){
////            return cities;
////        }
        if (orderBy.equals("name")) {
            cities.sort(Comparator.comparing(City::getName));
        } else if (orderBy.equals("population")) {
            cities.sort(Comparator.comparing(City::getPopulation));
        }
        return cities;
    }

    @PostMapping
    public void saveCity(@RequestParam String name, @RequestParam int population) {

        City city = new City(999, name, population);
        cities.add(city);
    }

    @PostMapping(path = "/json",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCityJson(@RequestBody City city) {
        cities.add(city);
    }


}

package pl.lucky.controller;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;
import pl.lucky.model.City;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @RequestMapping("/{id}")
    public City getCity(@PathVariable(name = "id") int id) {
        List<City> cities = Arrays.asList(
                new City(1, "Warszawa", 170000),
                new City(2, "Gdańsk", 340123),
                new City(3, "Gdynia", 54632),
                new City(4, "Zakopane", 213123)
        );
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }


        return null;
    }

    @RequestMapping
    public List<City> getCities(@RequestParam/*(required = false)*/(defaultValue = "name") String orderBy) {
        List<City> cities = Arrays.asList(
                new City(1, "Warszawa", 170000),
                new City(2, "Gdańsk", 340123),
                new City(3, "Gdynia", 54632),
                new City(4, "Zakopane", 213123)
        );
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


}

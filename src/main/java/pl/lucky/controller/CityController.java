package pl.lucky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lucky.model.City;
import pl.lucky.repository.CityRepository;

import java.util.*;

//@RestController
@Controller
@RequestMapping("/cities")
public class CityController {

    private CityRepository cityRepo;
    private List<City> cities;

    @Autowired
    public CityController(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    public CityController() {
        cities = Collections.synchronizedList(new ArrayList<City>());
    }


    @GetMapping
    public String listCities(Model model) {
        List<City> cities = cityRepo.findAll();
        model.addAttribute("cityList", cities);
        return "list";
    }


    @PostMapping
    public String addCity(@ModelAttribute City cityModel, RedirectAttributes redirectAtt) {
        cityRepo.save(cityModel);
        redirectAtt.addFlashAttribute("message", "City added successfuly");
        return "redirect:/";
    }


//    @GetMapping("/{id}")
//    public City getCity(@PathVariable(name = "id") int id) {
//        for (City city : cities) {
//            if (city.getId() == id) {
//                return city;
//            }
//        }
//        return null;
//    }
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<City> getCities(@RequestParam/*(required = false)*/(defaultValue = "name") String orderBy) {
//
////        if (orderBy==null){
//////            return cities;
//////        }
//        if (orderBy.equals("name")) {
//            cities.sort(Comparator.comparing(City::getName));
//        } else if (orderBy.equals("population")) {
//            cities.sort(Comparator.comparing(City::getPopulation));
//        }
//        return cities;
//    }
//
//    @PostMapping
//    public void saveCity(@RequestParam String name, @RequestParam int population) {
//
//        City city = new City(999, name, population);
//        cities.add(city);
//    }
//
//    @PostMapping(path = "/json",
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void saveCityJson(@RequestBody City city) {
//        cities.add(city);
//    }

}

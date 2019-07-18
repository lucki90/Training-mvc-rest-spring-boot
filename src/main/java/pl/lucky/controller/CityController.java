package pl.lucky.controller;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lucky.model.City;

@RestController
public class CityController {

    @RequestMapping("/city")
    public City getCity(){

        return new City("Warszawa",170000);
    }



}

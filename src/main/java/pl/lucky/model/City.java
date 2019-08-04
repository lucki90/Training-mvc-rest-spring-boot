package pl.lucky.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@EnableWebMvc
@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }


}

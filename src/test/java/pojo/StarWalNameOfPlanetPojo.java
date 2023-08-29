package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWalNameOfPlanetPojo {
    //add parameter @JsonIgnoreProperties(ignoreUnknown = true)  to help with defile only field you want
    //before add parameter you have to field all of them to get what you are looking for

    private String name;
//    private String rotation_period;
//    private String orbital_period;
//    private String diameter;
//    private String climate;
//    private String gravity;
    private String terrain;
//    private String surface_water;
    private String population;
//    private String created;
//    private String edited;
//    private String url;
//    private List<String> residents;
//    private List<String> films;



}

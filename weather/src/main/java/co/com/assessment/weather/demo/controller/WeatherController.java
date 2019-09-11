package co.com.assessment.weather.demo.controller;

import co.com.assessment.weather.demo.enumeration.LocationEnum;
import co.com.assessment.weather.demo.model.Weather;
import co.com.assessment.weather.demo.service.api.WeatherService;
import co.com.assessment.weather.demo.util.RestResourceConstants;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(RestResourceConstants.WEATHER_RESOURCE)
public class WeatherController {


  private final WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @RequestMapping(value = RestResourceConstants.SEPARATOR
      + RestResourceConstants.LOCATION, method = {RequestMethod.GET,
      RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Weather> getWeather(@NotNull @PathVariable LocationEnum location) {

    Weather response = weatherService.getWeather(location);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}

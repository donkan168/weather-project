package co.com.assessment.weather.demo.service.api;

import co.com.assessment.weather.demo.enumeration.LocationEnum;
import co.com.assessment.weather.demo.model.Weather;

public interface WeatherService {

  Weather getWeather(LocationEnum request);
}

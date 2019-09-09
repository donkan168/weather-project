package co.com.assessment.weather.demo.client.delegate.api;

import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.model.Weather;

public interface WeatherClientDelegate {

  Weather getWeather(WeatherRequestDTO requestDTO);
}
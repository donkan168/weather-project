package co.com.assessment.weather.demo.client.delegate.api;

import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.model.Weather;

public interface FirebaseClientDelegate {


  Weather getInfoFromBackupFirebase(WeatherRequestDTO requestDTO);

  void postInfoFromBackupFirebase(WeatherRequestDTO mainRequest, Weather weather);

}

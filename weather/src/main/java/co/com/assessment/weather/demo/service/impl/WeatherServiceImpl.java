package co.com.assessment.weather.demo.service.impl;

import co.com.assessment.weather.demo.client.delegate.api.WeatherClientDelegate;
import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.enumeration.LocationEnum;
import co.com.assessment.weather.demo.model.Daily;
import co.com.assessment.weather.demo.model.Weather;
import co.com.assessment.weather.demo.service.api.WeatherService;
import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

  private final WeatherClientDelegate weatherClientDelegate;

  public WeatherServiceImpl(
      WeatherClientDelegate weatherClientDelegate) {
    this.weatherClientDelegate = weatherClientDelegate;
  }

  @Override
  public Weather getWeather(LocationEnum request) {

    Weather response = weatherClientDelegate.getWeather(
        WeatherRequestDTO.builder().latitude(request.getLatitude())
            .longitude(request.getLongitude()).build());
    calculateDays(response);
    return response;
  }

  private void calculateDays(Weather response) {
    Daily daily = response.getDaily();
    Calendar today = Calendar.getInstance();
    for (int i = 0; i < daily.getData().size(); i++) {
      today.setTimeInMillis(daily.getData().get(i).getTime());
      daily.getData().get(i)
          .setDay(translateDayOfWeek(today.get(Calendar.DAY_OF_WEEK) + getNexNumber(i)));
    }
  }

  private int getNexNumber(int i) {
    return i < 7 ? i : 0;
  }

  private String translateDayOfWeek(int dayOfWeek) {

    String day = StringUtils.EMPTY;
    switch (dayOfWeek) {
      case 1:
        day = "Sun";
        break;
      case 2:
        day = "Mon";
        break;
      case 3:
        day = "Tue";
        break;
      case 4:
        day = "Wed";
        break;
      case 5:
        day = "Thu";
        break;
      case 6:
        day = "Fri";
        break;
      case 7:
        day = "Sat";
        break;
    }
    return day;
  }

}

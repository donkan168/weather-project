package co.com.assessment.weather.demo.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import co.com.assessment.weather.demo.client.delegate.api.WeatherClientDelegate;
import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.enumeration.LocationEnum;
import co.com.assessment.weather.demo.model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class WeatherServiceImplTest {

  @Mock
  private WeatherClientDelegate weatherClientDelegate;

  @InjectMocks
  private WeatherServiceImpl weatherService;


  @Value("classpath:files/weather/forecast-weather-get-response.json")
  private InputStream forecastResponse;


  @Test
  public void givenRequestWhenDataIsOkThenReturnAllWeatherData() throws IOException {

    Weather wetherResponseMock = new ObjectMapper()
        .readValue(IOUtils.toString(forecastResponse, Charset.forName("UTF-8")), Weather.class);

    when(weatherClientDelegate.getWeather(any(WeatherRequestDTO.class)))
        .thenReturn(wetherResponseMock);

    Weather response = weatherService.getWeather(LocationEnum.LIVERPOOL);

    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getCurrently());
    Assert.assertNotNull(response.getDaily());

    Assert.assertEquals(wetherResponseMock.getCurrently()
        .getTemperature(), response.getCurrently().getTemperature());
  }

}

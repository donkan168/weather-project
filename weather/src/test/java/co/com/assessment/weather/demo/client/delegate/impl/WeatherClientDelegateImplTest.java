package co.com.assessment.weather.demo.client.delegate.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.model.Weather;
import co.com.assessment.weather.demo.util.Sample;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
public class WeatherClientDelegateImplTest {

  @Mock
  private FirebaseClientDelegateImpl firebaseClientDelegate;

  @Mock
  private OkHttpClient httpsConfiguration;

  @InjectMocks
  private WeatherClientDelegateImpl weatherClientDelegate;

  @Value("classpath:files/weather/forecast-weather-get-response.json")
  private InputStream forecastResponse;

  @Before
  public void setUp() throws Exception {

    ReflectionTestUtils.setField(weatherClientDelegate, "urlBase", "https://proyecto-prueba-4e645.firebaseio.com");
    ReflectionTestUtils.setField(weatherClientDelegate, "apiKey", "e6bde58df44d5c75a513b6c48613ae53");
  }

  @Test
  public void givenRequestWhenDataIsOkThenReturnAllWeatherData() {

    Call responseCall = Sample.buildCall(forecastResponse);

    when(httpsConfiguration.newCall(any(Request.class)))
        .thenReturn(responseCall);

    FirebaseClientDelegateImpl firebaseClientDelegate2 = Mockito.spy(new FirebaseClientDelegateImpl(
        httpsConfiguration));
    Mockito.doNothing().when(firebaseClientDelegate2)
        .postInfoFromBackupFirebase(any(WeatherRequestDTO.class), any(Weather.class));

    Weather response = weatherClientDelegate.getWeather(Sample.buildWeatherRequestDTO());

    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getCurrently());
    Assert.assertNotNull(response.getDaily());
  }

}

package co.com.assessment.weather.demo.client.delegate.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import co.com.assessment.weather.demo.client.delegate.api.WeatherClientDelegate;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;


@EnableAutoConfiguration
@AutoConfigureWireMock(port = 8877)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class WeatherClientDelegateImplIntegrationTest {

  @Mock
  private FirebaseClientDelegateImpl firebaseClientDelegate;

  private OkHttpClient httpsConfiguration = spy(OkHttpClient.class);

  @InjectMocks
  private WeatherClientDelegateImpl weatherClientDelegate;

  @Value("classpath:files/weather/forecast-weather-get-response.json")
  private InputStream forecastResponse;

  @Before
  public void setUp() throws Exception {

    ReflectionTestUtils.setField(weatherClientDelegate, "httpsConfiguration", httpsConfiguration);
    ReflectionTestUtils.setField(weatherClientDelegate, "urlBase", "http://localhost:8877/forecast/e6bde58df44d5c75a513b6c48613ae53/40.416775,-3.703790?exclude=minutely,hourly,alerts,flags");
    ReflectionTestUtils.setField(weatherClientDelegate, "apiKey", "e6bde58df44d5c75a513b6c48613ae53");
  }


  @Test
  public void givenRequestWhenDataIsOkThenReturnAllWeatherData() {

    Weather response = weatherClientDelegate.getWeather(Sample.buildWeatherRequestDTO());

    Assert.assertNotNull(response);
  }

}

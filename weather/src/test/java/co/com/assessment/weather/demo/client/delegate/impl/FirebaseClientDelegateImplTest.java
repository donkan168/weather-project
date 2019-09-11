package co.com.assessment.weather.demo.client.delegate.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import co.com.assessment.weather.demo.config.FireBaseConfiguration;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;


@RunWith(SpringRunner.class)
public class FirebaseClientDelegateImplTest {


  @MockBean
  private FireBaseConfiguration fireBaseConfiguration;

  @Mock
  private OkHttpClient httpsConfiguration;

  @InjectMocks
  private FirebaseClientDelegateImpl weatherClientDelegate;

  @Value("classpath:files/weather/forecast-weather-get-response.json")
  private InputStream forecastResponse;

  @Before
  public void setUp() {

    ReflectionTestUtils.setField(weatherClientDelegate, "urlBackup", "https://localhost/{location}.json");
  }

  @Test
  public void givenRequestOkWhenDataIsOkThenReturnAllWeatherDataFromBackUp() {

    Call responseCall = Sample.buildCall(forecastResponse);

    when(httpsConfiguration.newCall(any(Request.class)))
        .thenReturn(responseCall);

    Weather response = weatherClientDelegate
        .getInfoFromBackupFirebase(Sample.buildWeatherRequestDTO());

    Assert.assertNotNull(response);
  }


  @Test
  public void givenRequestOkWhenDataIsOkThenSaveTheWeatherDataInBackUp() {

    weatherClientDelegate
        .postInfoFromBackupFirebase(Sample.buildWeatherRequestDTO(), new Weather());
  }
}

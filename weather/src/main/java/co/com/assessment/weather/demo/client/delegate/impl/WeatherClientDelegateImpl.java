package co.com.assessment.weather.demo.client.delegate.impl;

import co.com.assessment.weather.demo.client.delegate.api.FirebaseClientDelegate;
import co.com.assessment.weather.demo.client.delegate.api.WeatherClientDelegate;
import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.model.Weather;
import co.com.assessment.weather.demo.util.RestResourceConstants;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherClientDelegateImpl extends AbstractCommonClientDelegate implements
    WeatherClientDelegate {


  private final FirebaseClientDelegate firebaseClientDelegate;
  private final OkHttpClient httpsConfiguration;

  @Value("${forecast.io.services.apikey}")
  private String apiKey;

  @Value("${forecast.io.services.url}")
  private String urlBase;

  public WeatherClientDelegateImpl(
      FirebaseClientDelegate firebaseClientDelegate,
      OkHttpClient httpsConfiguration) {
    this.firebaseClientDelegate = firebaseClientDelegate;
    this.httpsConfiguration = httpsConfiguration;
  }

  @Override
  public Weather getWeather(WeatherRequestDTO requestDTO) {

    try {
      Request request = new Request.Builder()
          .url(buildUrl(requestDTO))
          .get().build();

      Response response = httpsConfiguration.newCall(request).execute();
      Weather weatherResponse = parseResponse(response);
      firebaseClientDelegate.postInfoFromBackupFirebase(requestDTO, weatherResponse);

      return weatherResponse;
    } catch (IOException e) {
      log.error("Error trying to retrieve weather information of the provider");
      log.info("Service response with information in backup");
      return firebaseClientDelegate.getInfoFromBackupFirebase(requestDTO);
    }
  }

  private String buildUrl(WeatherRequestDTO requestDTO) {
    String url = urlBase.replace(RestResourceConstants.FORECAST_IO_API_KE, apiKey)
        .replace(RestResourceConstants.FORECAST_IO_LATITUDE, requestDTO.getLatitude())
        .replace(RestResourceConstants.FORECAST_IO_LONGITUDE, requestDTO.getLongitude());
    return url;
  }
}

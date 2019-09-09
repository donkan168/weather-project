package co.com.assessment.weather.demo.client.delegate.impl;

import co.com.assessment.weather.demo.model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.Response;

public abstract class AbstractCommonClientDelegate {

  public Weather parseResponse(Response response) {
    try {
      return new ObjectMapper().readValue(response.body().byteString().utf8(), Weather.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

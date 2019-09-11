package co.com.assessment.weather.demo.util;

import co.com.assessment.weather.demo.dto.WeatherRequestDTO;
import co.com.assessment.weather.demo.enumeration.LocationEnum;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Timeout;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class Sample {


  public static final String URL_TEST = "https://localhost";
  public static final String CITY_MADRID = "MADRID";
  public static final String CITY_SANTORINI = "SANTORINI";

  public static WeatherRequestDTO buildWeatherRequestDTO() {
    return WeatherRequestDTO.builder().latitude(LocationEnum.MADRID.getLatitude())
        .longitude(LocationEnum.MADRID.getLongitude()).build();
  }

  public static Call buildCall(InputStream forecastResponse) {
    return new Call() {
      @NotNull
      @Override
      public Request request() {
        return new Request.Builder()
            .url("https://proyecto-prueba-4e645.firebaseio.com")
            .get().build();
      }

      @NotNull
      @Override
      public Response execute() throws IOException {
        return new Response.Builder()
            .code(HttpStatus.OK.value())
            .request(new Request.Builder()
                .url("https://proyecto-prueba-4e645.firebaseio.com")
                .get().build())
            .protocol(Protocol.HTTP_2)
            .message("")
            .body(ResponseBody.create(
                IOUtils.toString(forecastResponse, Charset.forName(StandardCharsets.UTF_8.name())),
                MediaType.get("application/json; charset=utf-8")
            ))
            .build();
      }

      @Override
      public void enqueue(@NotNull Callback callback) {
      }

      @Override
      public void cancel() {
      }

      @Override
      public boolean isExecuted() {
        return true;
      }

      @Override
      public boolean isCanceled() {
        return false;
      }

      @NotNull
      @Override
      public Timeout timeout() {
        return null;
      }

      @NotNull
      @Override
      public Call clone() {
        return this;
      }
    };
  }
}

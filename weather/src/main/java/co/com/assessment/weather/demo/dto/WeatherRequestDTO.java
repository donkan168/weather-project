package co.com.assessment.weather.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class WeatherRequestDTO {

  private String latitude;
  private String longitude;

}

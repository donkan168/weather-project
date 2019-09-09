package co.com.assessment.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Day {

  @JsonProperty("day")
  public String day;
  @JsonProperty("time")
  public Integer time;
  @JsonProperty("summary")
  public String summary;
  @JsonProperty("icon")
  public String icon;
  @JsonProperty("temperatureMin")
  public Double temperatureMin;
  @JsonProperty("temperatureMax")
  public Integer temperatureMax;

}
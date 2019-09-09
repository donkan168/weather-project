package co.com.assessment.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currently {

  @JsonProperty("summary")
  public String summary;
  @JsonProperty("icon")
  public String icon;
  @JsonProperty("nearestStormDistance")
  public Integer nearestStormDistance;
  @JsonProperty("precipType")
  public String precipType;
  @JsonProperty("temperature")
  public Integer temperature;

}